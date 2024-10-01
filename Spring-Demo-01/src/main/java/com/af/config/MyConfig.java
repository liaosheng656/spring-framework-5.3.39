package com.af.config;

import com.af.condition.MyCondition;
import com.af.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
@Conditional(value = {MyCondition.class})
public class MyConfig {

	@Bean
	public StudentService studentService2(){
		System.out.println("studentService2被执行");
		return new StudentService();
	}

}
