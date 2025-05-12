package com.af;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.af")
//@EnableWebMvc
public class MySpringApplication {

	public static void main(String[] args) {
		run(MySpringApplication.class);
		System.out.println("启动完成");
	}

	public static ConfigurableApplicationContext run(Class config) {
		AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
		applicationContext.register(config);
		applicationContext.refresh();

		startTomcat(applicationContext);

		return applicationContext;

	}

	private static Tomcat startTomcat(AnnotationConfigWebApplicationContext applicationContext) {
		Tomcat tomcat = new Tomcat();

		Server server = tomcat.getServer();
		Service service = server.findService("Tomcat");

		Connector connector = new Connector();
		connector.setPort(8080);

		Engine engine = new StandardEngine();
		engine.setDefaultHost("localhost");

		Host host = new StandardHost();
		host.setName("localhost");

		String contextPath = "";
		Context context = new StandardContext();
		context.setPath(contextPath);
		context.addLifecycleListener(new Tomcat.FixContextListener());

		host.addChild(context);
		engine.addChild(host);

		service.setContainer(engine);
		service.addConnector(connector);

		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		tomcat.addServlet(contextPath, "dispatcher", dispatcherServlet);
		context.addServletMappingDecoded("/*", "dispatcher");
//		ContextRefreshedEvent contextRefreshedEvent = new ContextRefreshedEvent(applicationContext);
//		dispatcherServlet.onApplicationEvent(contextRefreshedEvent);
		try {
			tomcat.start();
		} catch (LifecycleException e) {
			e.printStackTrace();
		}

		return tomcat;
	}
}
