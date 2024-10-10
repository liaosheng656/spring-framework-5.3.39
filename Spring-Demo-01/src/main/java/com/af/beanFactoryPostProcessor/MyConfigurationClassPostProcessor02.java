package com.af.beanFactoryPostProcessor;

import com.af.service.StudentService;
import com.af.service.UserService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


public class MyConfigurationClassPostProcessor02
//        extends ConfigurationClassPostProcessor
        implements BeanDefinitionRegistryPostProcessor
{


    /**
     * 这里后调用
     * BeanFactoryPostProcessor只有这个方法
     * @param beanFactory the bean factory used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("先调用postProcessBeanFactory");
        if(beanFactory instanceof DefaultListableBeanFactory){
            DefaultListableBeanFactory defaultBeanFactory = (DefaultListableBeanFactory) beanFactory;
            int beanDefinitionCount = defaultBeanFactory.getBeanDefinitionCount();
            System.out.println("还可以自定义BeanDefinition或创建bean");
            System.out.println("自定义postProcessBeanFactory回调，beanDefinitionCount = "+beanDefinitionCount);
        }
        System.out.println("postProcessBeanFactory----bean的后置处理器");
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        System.out.println("bean定义个数="+beanDefinitionCount);
    }

    /**
     * 先调用
     *
     * BeanDefinitionRegistryPostProcessor有这个方法，直接实现BeanFactoryPostProcessor是没有这个方法的
     * @param registry the bean definition registry used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.println("先调用postProcessBeanDefinitionRegistry");
        System.out.println("可以注册bean定义的bean后置处理器BeanDefinitionRegistryPostProcessor");
        RootBeanDefinition rootBeanDefinition02 = new RootBeanDefinition();
        rootBeanDefinition02.setBeanClass(MyConfigurationClassPostProcessor03.class);
        registry.registerBeanDefinition("myConfigurationClassPostProcessor03",rootBeanDefinition02);

    }

    @Bean("studentService05")
    public StudentService studentService01(){
        return new StudentService();
    }
}
