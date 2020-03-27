package com.imooc.activemq.producer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling      // 是否开启
public class BootMqProducerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootMqProducerApplication.class, args);
    }

}
