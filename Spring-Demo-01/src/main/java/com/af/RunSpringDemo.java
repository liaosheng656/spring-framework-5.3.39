package com.af;


//import lombok.extern.slf4j.Slf4j;
import com.af.service.StudentService;
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

    public static void main(String[] args) {

        //无配置文件启动
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RunSpringDemo.class);
        try{
            Object studentService = context.getBean("studentService");
            if(studentService != null){
                StudentService student = (StudentService) studentService;
                student.studentServiceTest01();
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
    }

}
