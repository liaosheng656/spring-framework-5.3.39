package com.af.annotation;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface MyAnnotation {

	@AliasFor(annotation = Component.class)
	String value() default "";

	boolean init() default false;
}
