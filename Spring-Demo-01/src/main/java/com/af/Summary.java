package com.af;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultSingletonBeanRegistry;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.core.type.AnnotationMetadata;
import com.af.beanFactoryPostProcessor.MyConfigurationClassPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanFactory;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import com.af.service.dependent.DependsOnConfig;


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
         * {@link AbstractBeanFactory#doGetBean}
         * {@link  AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
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
         *   4、实例化/属性填充的过程中-判断是否依赖其他类/bean-->先创建依赖的bean-->可能存在循环依赖-->
         *     4.1、构造方法-循环依赖
         *        @see AbstractBeanFactory#doGetBean
         *        //（A）bean使用有参构造参数实例化时-解析构造方法上的参数param
         *        4.1.1、{@link org.springframework.beans.factory.support.ConstructorResolver#createArgumentArray}
         *        //先判断requestingBeanName是否为懒加载，如果懒加载则返回一个代理对象
         *        //bean使用有参构造参数实例化时-寻找有没有这个bean，没有就创建（param参数）bean-最后来到这里-getBean(param)
         *        4.1.2、{@link DependencyDescriptor#resolveCandidate(String, Class, BeanFactory)}
         *        4.1.3、调用beanFactory.getBean(param)方法，如果已经有这个bean，则返回
         *        4.1.4、如果没有这个（param）bean，则创建又走bean的生命周期
         *        4.1.5、如果发现（param）也依赖（A），那凉凉，（A）和（param）循环依赖都无法实例化，报错
         *        4.1.6、加个@Lazy可以解决构造方法循环依赖
         *     4.2、@DependsOn-循环依赖
         *        @see DependsOnConfig
         *        4.2.1、@DependsOn这个注解只关注了这个注解的值，和一般的感觉不一样，
         *              产生循环依赖时，根本没有走到实例化那一步
         *        {@link AbstractBeanFactory#doGetBean}
         *        {@link DefaultSingletonBeanRegistry#isDependent(String, String)}
         *        4.2.2、加@Lazy也不能解决DependsOn循环依赖，单方面加@Lazy，启动时直接报错
         *        4.2.3、双方加@Lazy，只是启动时不报错，真正使用的时候会报错
         *
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
