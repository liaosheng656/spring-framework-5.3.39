package com.af.beanFactoryPostProcessor;

import com.af.service.StudentService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.Bean;


public class MyConfigurationClassPostProcessor04
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
        System.out.println("先调用postProcessBeanFactory4");
        if(beanFactory instanceof DefaultListableBeanFactory){
            DefaultListableBeanFactory defaultBeanFactory = (DefaultListableBeanFactory) beanFactory;
            int beanDefinitionCount = defaultBeanFactory.getBeanDefinitionCount();
            System.out.println("还可以自定义BeanDefinition或创建bean4");
            System.out.println("自定义postProcessBeanFactory回调4，beanDefinitionCount = "+beanDefinitionCount);
        }
        System.out.println("postProcessBeanFactory----bean的后置处理器4");
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
    }

    @Bean("studentService05")
    public StudentService studentService01(){
        return new StudentService();
    }
}
