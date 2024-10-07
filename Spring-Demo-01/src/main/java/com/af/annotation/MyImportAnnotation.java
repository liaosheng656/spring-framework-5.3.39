package com.af.annotation;

import com.af.Import1.ImportBeanDefinitionRegistrarTest01;
import com.af.Import1.ImportSelectorTest01;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration
@Import({ImportSelectorTest01.class, ImportBeanDefinitionRegistrarTest01.class})
public @interface MyImportAnnotation {

	boolean init() default false;
}
