package com.af.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public interface TestComponentsService {

    default
    @Bean("studentService06")
    public StudentService studentService06(){
        return new StudentService();
    }
}
