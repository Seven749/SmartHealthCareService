package com.sevenrecy.smarthealthcareservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.sevenrecy.smarthealthcareservice.dao")
@SpringBootApplication
public class SmartHealthCareServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SmartHealthCareServiceApplication.class, args);
    }

}
