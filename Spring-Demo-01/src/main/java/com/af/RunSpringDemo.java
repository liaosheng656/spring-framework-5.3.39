package com.af;


//import lombok.extern.slf4j.Slf4j;
import com.af.annotation.MyAnnotation;
import com.af.service.PerSonService;
import com.af.service.StudentService;
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
@MyAnnotation(init = false)
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
            //�
        }catch (Exception e){
            e.printStackTrace();
        }
//		RunSpringServiceDemo bean = context.getBeanFactory().createBean(RunSpringServiceDemo.class);
//		AFAnnotationConfigApplicationContext context = new AFAnnotationConfigApplicationContext(RunSpringDemo.class);
        System.out.println("finish-333333");
        System.out.println("file.encoding: {}"+System.getProperty("file.encoding"));
        System.out.println("怕了怕了");
        System.out.println("解析@Scope注解，看看是否有设置代理,类为,什么鬼东西");
        System.out.println("你大爷和搜到合法搜地金佛啊受打击无参构造方法");
		System.out.println("获取类型");
    }

}
