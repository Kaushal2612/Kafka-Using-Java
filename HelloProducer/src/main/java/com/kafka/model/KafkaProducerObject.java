package com.kafka.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * This is the pojo class
 */
public class KafkaProducerObject {
    @JsonProperty("name")
    private String name;
    @JsonProperty("message")
    private String message;

    public KafkaProducerObject() {
    }

    public KafkaProducerObject(String name, String message) {
        this.name = name;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
