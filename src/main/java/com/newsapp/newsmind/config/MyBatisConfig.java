package com.newsapp.newsmind.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.newsapp.newsmind.mapper")
public class MyBatisConfig {
}