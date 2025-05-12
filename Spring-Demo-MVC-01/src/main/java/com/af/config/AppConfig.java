package com.af.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Configuration
public class AppConfig {


//	@Bean
//	public View mappingJackson2JsonView(){
//		return new MappingJackson2JsonView();
//	}

	@Bean
	public MappingJackson2JsonView viewResolver(){
		return new MappingJackson2JsonView();
	}
}
