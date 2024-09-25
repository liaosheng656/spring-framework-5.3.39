package com.af;


import org.springframework.context.annotation.*;

import java.io.IOException;

//@Configuration
/**
 * ��дSring
 */
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)//CGLIB����
@Configuration
@ComponentScan("com.af")
public class RunSpringDemo {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		//������������
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
//		context.getBean("");
		//����bean
//		RunSpringServiceDemo bean = context.getBeanFactory().createBean(RunSpringServiceDemo.class);
//		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringDemo.class);
		System.out.println("finish-333333");
	}

}
