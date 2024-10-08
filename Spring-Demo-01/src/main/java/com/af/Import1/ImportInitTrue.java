package com.af.Import1;

import com.af.service.StudentService;
import org.springframework.context.annotation.Bean;

/**
 *
 */
public class ImportInitTrue {

	public ImportInitTrue(){
		System.out.println("ImportInitTrue构造方法被调用");
	}

	public void ImportInitTrueTest01(){
		System.out.println("调用ImportInitTrueTest01方法");
	}

    @Bean("studentService02")
    public StudentService studentService01(){
        return new StudentService();
    }
}
