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


@Component
public class MyConfigurationClassPostProcessor
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
        BeanDefinition orderService = registry.getBeanDefinition("orderService");
        if(orderService != null){
            //class，表示Bean类型
            //scope，表示Bean作用域，单例或原型等
            //lazyInit:表示Bean是否是懒加载
            //initMethodName:表示Bean初始化时要执行的方法
            //destroyMethodName:表示Bean销毁时要执行的方法
            orderService.setInitMethodName("orderServiceTest01");
            String beanClassName = orderService.getBeanClassName();
            System.out.println("设置orderService初始化方法,beanClassName = "+beanClassName);

            RootBeanDefinition rootBeanDefinition = new RootBeanDefinition();
            //类路径
//            rootBeanDefinition.setBeanClassName("com.af.service.StudentService");
//            rootBeanDefinition.setBeanClassName(StudentService.class.getName());
            rootBeanDefinition.setBeanClass(StudentService.class);
            // 设置构造函数参数
            ConstructorArgumentValues constructorArgumentValues = new ConstructorArgumentValues();
            constructorArgumentValues.addIndexedArgumentValue(0, new UserService());
            constructorArgumentValues.addIndexedArgumentValue(1,new UserService());
            //指定构造方法
            rootBeanDefinition.setConstructorArgumentValues(constructorArgumentValues);
//            //自动配置AUTOWIRE_BY_TYPE还是AUTOWIRE_BY_NAME
//            rootBeanDefinition.setAutowireCandidate(false);
//            //AUTOWIRE_BY_TYPE
//            rootBeanDefinition.setAutowireMode(RootBeanDefinition.AUTOWIRE_BY_TYPE);
            //如果是FactoryBean
//            rootBeanDefinition.setFactoryBeanName();
//            rootBeanDefinition.setFactoryMethodName();
            //bean定义名字---studentService
            registry.registerBeanDefinition("studentService",rootBeanDefinition);

            System.out.println("注册studentService-----bean定义");
        }
    }

    @Bean("studentService05")
    public StudentService studentService01(){
        return new StudentService();
    }
}
