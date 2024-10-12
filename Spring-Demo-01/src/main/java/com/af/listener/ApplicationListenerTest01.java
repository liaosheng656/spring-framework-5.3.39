package com.af.listener;

import com.af.event.ApplicationContextEventTest01;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 事件监听器
 */
@Component
public class ApplicationListenerTest01 implements ApplicationListener
        <ApplicationContextEventTest01>
{

//    @Override
//    public void onApplicationEvent(ApplicationEvent event) {
//        System.out.println("调用onApplicationEvent事件监听器，说明容器启动快结束了");
//        //事件源
//        Object source = event.getSource();
//        if(event == null){
//            System.out.println("调用onApplicationEvent，说明容器qi快结束了");
//            return;
//        }
//        //如果是这个来源
//        if(source instanceof AnnotationConfigApplicationContext){
//            AnnotationConfigApplicationContext applicationContext = (AnnotationConfigApplicationContext) source;
//            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
//            int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
//            System.out.println("beanDefinitionCount个数 = "+beanDefinitionCount);
//        }
//
//        if(event instanceof ApplicationContextEventTest01){
//            ApplicationContextEventTest01 eventTest01 = (ApplicationContextEventTest01) event;
//            System.out.println(eventTest01.getMessage()+"--------调用onApplicationEvent，说明容器启动快结束了");
//        }
//        System.out.println("调用onApplicationEvent，说明容器qi快结束了");
//    }

    @Override
    public void onApplicationEvent(ApplicationContextEventTest01 event) {
        System.out.println("调用onApplicationEvent事件监听器，说明容器启动快结束了");
        //事件源
        Object source = event.getSource();
        if(event == null){
            System.out.println("调用onApplicationEvent，说明容器qi快结束了");
            return;
        }
        //如果是这个来源
        if(source instanceof AnnotationConfigApplicationContext){
            AnnotationConfigApplicationContext applicationContext = (AnnotationConfigApplicationContext) source;
            ConfigurableListableBeanFactory beanFactory = applicationContext.getBeanFactory();
            int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
            System.out.println("beanDefinitionCount个数 = "+beanDefinitionCount);
        }

        System.out.println(event.getMessage()+"--------调用onApplicationEvent，说明容器启动快结束了");
    }
}
