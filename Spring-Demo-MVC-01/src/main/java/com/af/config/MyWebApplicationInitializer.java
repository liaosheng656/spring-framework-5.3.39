package com.af.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


@Component
public class MyWebApplicationInitializer implements WebApplicationInitializer {

	/** Logger available to subclasses. */
	protected final Log logger = LogFactory.getLog(getClass());

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		logger.info("进入我的方法了");
//		DispatcherServlet dispatcherServlet = new DispatcherServlet();
//		// 注册一个Servlet
//		ServletRegistration.Dynamic registration = servletContext.addServlet("app", dispatcherServlet);
//		registration.addMapping("/app/*");
//		registration.setLoadOnStartup(1);
	}
}
