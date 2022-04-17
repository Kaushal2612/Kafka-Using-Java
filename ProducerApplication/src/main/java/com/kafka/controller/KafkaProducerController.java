package com.kafka.controller;

import com.kafka.model.KafkaProducerObject;
import com.kafka.service.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/kafka/")
public class KafkaProducerController {

    @Autowired
    KafkaProducerService kafkaProducerService;

    /**
     * This class send message from Rest Endpoint to the Kafka Producer
     * @param kafkaProducerObject
     * @return
     */
    @PostMapping(value = "/producer")
    public String sendMessage(@RequestBody KafkaProducerObject kafkaProducerObject)
    {
        KafkaProducerService.send(kafkaProducerObject);
        return "Message sent Successfully to the topic";
    }

}