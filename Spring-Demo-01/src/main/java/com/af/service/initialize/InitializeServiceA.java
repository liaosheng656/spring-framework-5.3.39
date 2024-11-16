package com.af.service.initialize;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.InitDestroyAnnotationBeanPostProcessor;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class InitializeServiceA implements InitializingBean {

    /**
     * 初始化之前（bean后置处理器）回调调用
     * // @PostConstruct比 InitializingBean回调早一点点
     * @see InitDestroyAnnotationBeanPostProcessor#postProcessBeforeInitialization(Object, String)
     */
    @PostConstruct
    public void start(){
        System.out.println("initializeServiceA-@PostConstruct");
    }

    /**
     * 初始化bean
     * bean属性填充后
     * 如果指定了init方法，init方法之前
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("initializeServiceA属性填充已完成-（bean属性填充完成后-调用初始化方法）");
    }

    /**
     * 容器销毁前调用
     * @see AbstractApplicationContext#close()
     */
    @PreDestroy
    public void end(){
        System.out.println("initializeServiceA-销毁方法被调用");
    }
}
