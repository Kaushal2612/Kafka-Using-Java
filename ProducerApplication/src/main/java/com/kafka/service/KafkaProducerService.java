package com.kafka.service;

import com.kafka.config.StringProducer;
import com.kafka.constant.AppConstant;
import com.kafka.model.KafkaProducerObject;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.stereotype.Service;

import java.util.Properties;

/**
 * This class is to produce json message received from Rest API and send it to the topic
 */
@Service
public class KafkaProducerService {

    /**
     * This method produces message on to the topic
     * @param kafkaProducerObject
     */
    public static void send(KafkaProducerObject kafkaProducerObject) {
        System.out.println("Json Serializer for the Person : {}"+ kafkaProducerObject);

        Properties properties = getJsonProducerConfiguration();
        // Creating Producer
        KafkaProducer<String, KafkaProducerObject> kafkaProducer = new KafkaProducer<>(properties);

        ProducerRecord<String, KafkaProducerObject> record = new ProducerRecord<String, KafkaProducerObject>
                (AppConstant.JSON_TOPIC, kafkaProducerObject);

        kafkaProducer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger= LoggerFactory.getLogger(StringProducer.class);
                if (e== null) {
                    logger.info("Successfully received the details as: \n" +
                            "Topic:" + recordMetadata.topic() + "\n" +
                            "Partition:" + recordMetadata.partition() + "\n" +
                            "Offset" + recordMetadata.offset() + "\n" +
                            "Timestamp" + recordMetadata.timestamp());
                }

                else {
                    logger.error("Can't produce,getting error",e);

                }
            }
        });  ;
        kafkaProducer.flush();
        kafkaProducer.close();
    }

    /**
     * Set the properties configuration
     * @return
     */
    private static Properties getJsonProducerConfiguration () {
        String bootstrapServers = AppConstant.BOOTSTRAP_SERVER;
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());
        return props;
    }

}