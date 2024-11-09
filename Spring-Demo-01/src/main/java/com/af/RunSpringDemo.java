package com.af;


//import lombok.extern.slf4j.Slf4j;
import com.af.annotation.MyAnnotation;
import com.af.event.ApplicationContextEventTest01;
import com.af.service.HelloService;
import com.af.service.PerSonService;
import com.af.service.StudentService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.*;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

//@Configuration
/**
 * ��дSring
 */
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)//CGLIB����
@Configuration
@ComponentScan("com.af")
//@MyAnnotation(init = false)
@MyAnnotation(init = true)
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RunSpringDemo {

	/**
	 * 内部类也可以被扫到
	 * {@link org.springframework.context.annotation.ConfigurationClassParser#processMemberClasses}
	 * (org.springframework.context.annotation.ConfigurationClass,
	 * org.springframework.context.annotation.ConfigurationClassParser.SourceClass,
	 * java.util.function.Predicate)}
	 */
	@Component
	class  MemberRunSpringDemo{
		public MemberRunSpringDemo(){
			System.out.println("内部类MemberRunSpringDemo被调用了");
		}
	}
    public static void main(String[] args) {

		//扫描逻辑（关键）ConfigurationClassUtils.isConfigurationCandidate
		//判断扫描的文件是否可以成为bean定义ClassPathScanningCandidateComponentProvider.isCandidateComponent
        //@Component、ComponentScan、Import、ImportResource、@Configuration 有这些注解的都可以为配置类
        //核心创建bean、循环依赖
        /**
         * {@link org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean}
         * {@link  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
         */
		//无配置文件启动
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
        Object bean = context.getBean("&factoryBeanTest01");
        System.out.println(bean);
        try{
            Object studentService = context.getBean("studentService");
            if(studentService != null){
                StudentService student = (StudentService) studentService;
                student.studentServiceTest01();
            }
			Object perSonServiceObj = context.getBean("perSonService");
            if(perSonServiceObj != null){
				PerSonService perSonService  = (PerSonService) perSonServiceObj;
				perSonService.perSonYmlAndProperties();
			}
            Object helloServiceObj = context.getBean("helloService");
            if(helloServiceObj != null){
                HelloService helloService = (HelloService) helloServiceObj;
                helloService.HelloServiceTest();
            }
            Collection<ApplicationListener<?>> listenerList = context.getApplicationListeners();
            if(listenerList != null){
                for (ApplicationListener listener:listenerList) {
                    listener.onApplicationEvent(new ApplicationContextEventTest01(context,"发条信息"));
                    //启动完，发个事件
//                    listener.onApplicationEvent(new ContextRefreshedEvent(context));

                }
            }
            String[] singletonNames = context.getBeanFactory().getSingletonNames();
            String json = JSONObject.toJSONString(singletonNames);
            System.out.println("singletonNames = "+json);
            //�
        }catch (Exception e){
            e.printStackTrace();
        }
//		RunSpringServiceDemo bean = context.getBeanFactory().createBean(RunSpringServiceDemo.class);
//		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringDemo.class);
        System.out.println("finish-333333");
        System.out.println("file.encoding: "+System.getProperty("file.encoding"));
    }

}
