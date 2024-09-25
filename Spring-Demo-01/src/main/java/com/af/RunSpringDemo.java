package com.af;


import org.springframework.context.annotation.*;

import java.io.IOException;

//@Configuration
/**
 * 手写Sring
 */
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)//CGLIB代理
@Configuration
@ComponentScan("com.af")
public class RunSpringDemo {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		//创建容器对象
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
//		context.getBean("");
		//创建bean
//		RunSpringServiceDemo bean = context.getBeanFactory().createBean(RunSpringServiceDemo.class);
//		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringDemo.class);
		System.out.println("执行完了-----2222");

	}

}
