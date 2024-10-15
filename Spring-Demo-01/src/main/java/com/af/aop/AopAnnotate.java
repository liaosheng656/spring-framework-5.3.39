package com.af.aop;

import java.lang.annotation.*;

/**
 * @author szh
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Documented
@Inherited
public @interface AopAnnotate {

}
