package com.af.config;

import com.af.condition.MyCondition;
import com.af.service.OrderService;
import com.af.service.StudentService;
import org.springframework.context.annotation.*;

@Configuration
@Conditional(value = {MyCondition.class})
public class MyConfig {

    /**
     * 需要代理
     * @return
     */
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
	@Bean
	public StudentService studentService2(){
		System.out.println("studentService2被执行");
		return new StudentService();
	}

    /**
     * 需要代理
     * 代理生成两个bean
     * "scopedTarget.studentService7","studentService7"
     * 静态类也会生成bean
     * @return
     */
    @Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
    @Bean
    public static StudentService studentService7(){
        System.out.println("studentService7被执行");
        return new StudentService();
    }

    @Bean
    //相关的bean，如果设置了@Primary，优先级会变高
    @Primary
    public StudentService studentService7(OrderService orderService){
        System.out.println("studentService7被执行");
        return new StudentService();
    }
}
