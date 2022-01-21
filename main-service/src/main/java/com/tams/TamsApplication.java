package com.tams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class TamsApplication {

    public static void main(String[] args) {

        System.setProperty("pagehelper.banner" , "false");
        SpringApplication.run(TamsApplication.class , args);
        System.out.println(" ╮(￣▽ ￣)╭ 加载完毕....");

    }

}
