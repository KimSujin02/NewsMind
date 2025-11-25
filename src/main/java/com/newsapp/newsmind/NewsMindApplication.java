package com.newsapp.newsmind;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.newsapp.newsmind.mapper")
public class NewsMindApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewsMindApplication.class, args);
    }

}
