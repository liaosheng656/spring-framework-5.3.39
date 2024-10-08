package com.af;


//import lombok.extern.slf4j.Slf4j;
import com.af.annotation.MyAnnotation;
import com.af.service.HelloService;
import com.af.service.PerSonService;
import com.af.service.StudentService;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

//@Configuration
/**
 * ��дSring
 */
//@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)//CGLIB����
@Configuration
@ComponentScan("com.af")
//@MyAnnotation(init = false)
@MyAnnotation(init = true)
public class RunSpringDemo {

	/**
	 * 内部类也可以被扫到
	 * {@link org.springframework.context.annotation.ConfigurationClassParser#processMemberClasses
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
        //无配置文件启动
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
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
