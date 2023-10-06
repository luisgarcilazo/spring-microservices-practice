package com.example.employeeservice.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Value("${department.service.url}")
    private String departmentServiceUrl;

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
