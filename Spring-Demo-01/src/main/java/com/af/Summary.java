package com.af;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.type.AnnotationMetadata;
import com.af.beanFactoryPostProcessor.MyConfigurationClassPostProcessor;


/**
 * 总结
 */
public class Summary {
    public static void main(String[] args) {
        /**
         * 扫描逻辑
         *beanDefinition的扫描
         *  1、BeanFactoryPostProcessor、ImportSelector
         *  2、@Component(有这个注解一般都可以)、@ComponentScan、@Import、@ImportResource、@Configuration 有这些注解的都可以为配置类
         *  3、@Conditional条件注入
         *{@link ConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry(BeanDefinitionRegistry)}
         *扫描逻辑（关键）
         * {@link org.springframework.context.annotation.ConfigurationClassUtils#isConfigurationCandidate(AnnotationMetadata)}
         *
         *判断扫描的文件是否可以成为bean定义
         *{@link ClassPathScanningCandidateComponentProvider#isCandidateComponent(AnnotatedBeanDefinition beanDefinition)}
         **/

        //核心创建bean、循环依赖
        /**
         * 创建bean的核心方法
         * {@link org.springframework.beans.factory.support.AbstractBeanFactory#doGetBean}
         * {@link  org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
         */
        /**
         * bean生命周期
         *   1、BeanFactory.getBean(beanName)-->
         *   2、FactoryBean不会实例化-->填充属性(.getObject())-->FactoryBean初始化后-->
         *   3、普通bean-->bean实例化-->
         *     3.1、实例化-->构造方法/实例化选择
         *     @see AutowiredAnnotationBeanPostProcessor#determineCandidateConstructors(Class, String)
         *     寻找bean实例化的构造方法（优先级顺序）
         *       3.1.1、优先使用@Autowired(required = true)的构造方法
         *       3.1.2、使用BeanDefinition定义好的构造参数 {@link MyConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry}
         *       3.1.3、使用无参构造方法
         *   4、判断是否依赖其他类/bean-->先创建依赖的bean-->可能存在循环依赖-->
         *     4.1、循环依赖解决
         *   5、一些Aware回调-->
         *   6、Bean初始化前-->initializeBean初始化回调-->初始化方法-->Bean初始化后-->注册销毁方法-->加入单例池中-->
         *   7、销毁前调用销毁方法-->bean销毁-->容器销毁
         */

        /**
         * AOP/事务使用
         *   1、引入依赖-->写上@EnableAspectJAutoProxy注解-->
         *   2、启动扫描beanDefinition时import对应的注册AnnotationAwareAspectJAutoProxyCreator（bean的后置处理器）
         */
    }
}
