package com.kafka.config;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class MyProducer {

    public static void run() {
        // Creating Properties
        Properties properties = getProducerConfiguration();
        // Creating Producer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        // Creating Producer Record
        ProducerRecord<String, String> record = new ProducerRecord<>("first2", "Hello User !!");
        // sending the data
        kafkaProducer.send(record, new Callback() {
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                Logger logger= LoggerFactory.getLogger(MyProducer.class);
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
    private static Properties getProducerConfiguration () {
        String bootstrapServers = "127.0.0.1:9092";
        Properties props = new Properties();
        props.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return props;
    }

}
