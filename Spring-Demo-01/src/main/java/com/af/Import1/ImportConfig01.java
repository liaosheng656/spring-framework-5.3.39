package com.af.Import1;

import com.af.annotation.MyAnnotation;
import com.af.annotation.MyImportAnnotation;
import com.af.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 导入
 * @see org.springframework.transaction.annotation.EnableTransactionManagement 开始事务注解-可以看看
 *
 */
@MyImportAnnotation(init = true)
public class ImportConfig01 {

    @Bean("studentService01")
    public StudentService studentService01(){
        return new StudentService();
    }
}
