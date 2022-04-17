package com.kafka.config;

import com.kafka.constant.AppConstant;
import com.kafka.model.KafkaConsumerMessage;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * This class consumes data from the json topic
 */
@Configuration
public class JsonConsumer {

    @Value("kafka.topic")
    public static String topic;

    public static void run() {

        String myTopic = AppConstant.JSON_TOPIC;
        Logger logger = LoggerFactory.getLogger(JsonConsumer.class.getName());
        // Create Consumer Properties
        Properties properties = getConsumerConfiguration();
        //creating consumer
        KafkaConsumer<String, KafkaConsumerMessage> consumer = new KafkaConsumer<String, KafkaConsumerMessage>(properties);
        //Subscribing
        consumer.subscribe(Arrays.asList(myTopic));
        //polling
        while (true) {
            ConsumerRecords<String, KafkaConsumerMessage> records = consumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, KafkaConsumerMessage> record : records) {
                logger.info("Key: " + record.key() + ", Value:" + record.value().getName());
                logger.info("Key: " + record.key() + ", Value:" + record.value().getMessage());
                logger.info("Partition:" + record.partition() + ",Offset:" + record.offset());
            }

        }
    }

    /**
     * Set the properties configuration
     *
     * @return
     */
    private static Properties getConsumerConfiguration() {
        String bootstrapServers = AppConstant.BOOTSTRAP_SERVER;
        String grp_id = AppConstant.GROUP_ID;
        //Creating consumer properties
        Properties properties = new Properties();
        properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class.getName());
        properties.setProperty(JsonDeserializer.VALUE_DEFAULT_TYPE, "com.kafka.model.KafkaConsumerMessage");
        properties.put(JsonDeserializer.USE_TYPE_INFO_HEADERS,false);
        properties.setProperty(JsonDeserializer.TRUSTED_PACKAGES, "*");
        properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, grp_id);
        properties.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        return properties;
    }
}
