package com.af.Import1;

import com.af.service.StudentService;
import com.af.service.TestComponentsService;
import org.springframework.context.annotation.Bean;

/**
 *ImportInitTrue 为ImportSelector导入，可以作为配置类，配置类的父接口默认方法也可以注入bean
 */
public class ImportInitTrue implements TestComponentsService {

	public ImportInitTrue(){
		System.out.println("ImportInitTrue构造方法被调用");
	}

	public void ImportInitTrueTest01(){
		System.out.println("调用ImportInitTrueTest01方法");
	}

    @Bean("studentService011")
    public StudentService studentService011(){
        return new StudentService();
    }
}
