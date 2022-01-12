package com.tams;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.tams.mapper")
public class TamsApplication {

    public static void main(String[] args) {

        SpringApplication.run(TamsApplication.class , args);
    }

}
