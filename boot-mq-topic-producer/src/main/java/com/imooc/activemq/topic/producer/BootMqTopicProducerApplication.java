package com.imooc.activemq.topic.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BootMqTopicProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqTopicProducerApplication.class, args);
    }

}
