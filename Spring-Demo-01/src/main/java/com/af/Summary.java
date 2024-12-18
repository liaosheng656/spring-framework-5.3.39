package com.af;

import com.af.service.TransactionalService;
import com.af.service.dependent.AutowiredServiceA;
import com.af.service.initialize.InitializeServiceA;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.weaver.tools.JoinPointMatch;
import org.springframework.aop.TargetSource;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.aspectj.autoproxy.AspectJAwareAdvisorAutoProxyCreator;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.aop.framework.autoproxy.AbstractAdvisorAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.AbstractAutoProxyCreator;
import org.springframework.aop.interceptor.ExposeInvocationInterceptor;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.TypeConverter;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.CommonAnnotationBeanPostProcessor;
import org.springframework.context.annotation.ConfigurationClassPostProcessor;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.type.AnnotationMetadata;
import com.af.beanFactoryPostProcessor.MyConfigurationClassPostProcessor;
import com.af.service.dependent.DependsOnConfig;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory;
import  com.af.aop.AopAspect;
import  org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.interceptor.TransactionInterceptor;
import java.lang.reflect.Method;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.transaction.support.ResourceHolderSupport;

import java.util.List;
import java.util.Set;


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
         * FactoryBean 处理
         * {@link AbstractBeanFactory#getObjectForBeanInstance(Object, String, String, RootBeanDefinition)}
         * {@link  AbstractAutowireCapableBeanFactory#createBean(java.lang.String, org.springframework.beans.factory.support.RootBeanDefinition, java.lang.Object[])}
         */
        /**
         * bean生命周期
         *   1、BeanFactory.getBean(beanName)-->
         *      @see  AbstractBeanFactory#doGetBean
         *   2、FactoryBean不会实例化-->填充属性(.getObject())返回-->FactoryBean初始化后-->
         *      @see AbstractBeanFactory#getObjectForBeanInstance(Object, String, String, RootBeanDefinition)
         *   3、普通bean-->bean实例化-->
         *      @see AbstractAutowireCapableBeanFactory#createBean(String, RootBeanDefinition, Object[])
         *     3.1、实例化-->构造方法/实例化选择
         *      @see AbstractAutowireCapableBeanFactory#createBeanInstance(String, RootBeanDefinition, Object[])
         *      @see AutowiredAnnotationBeanPostProcessor#determineCandidateConstructors(Class, String)
         *     寻找bean实例化的构造方法（优先级顺序）
         *       3.1.1、优先使用@Autowired(required = true)的构造方法
         *       3.1.2、使用BeanDefinition定义好的构造参数
         *          @see MyConfigurationClassPostProcessor#postProcessBeanDefinitionRegistry
         *       3.1.3、使用无参构造方法
        *     3.2、属性填充
         *      @see AbstractAutowireCapableBeanFactory#populateBean(String, RootBeanDefinition, BeanWrapper)
         *    3.3、属性填充-属性解析-@Autowired和@Resource注入的区别
         *      3.3.1、@Autowired默认/只能按类型注入
         *          @see AutowiredAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)
         *          @see DefaultListableBeanFactory#findAutowireCandidates(String, Class, DependencyDescriptor)
     *          3.3.2、@Resource默认按名字注入，按名字找不到，就按类型找，如果指定名字，则只能按名字找
     *              @see CommonAnnotationBeanPostProcessor#postProcessProperties(PropertyValues, Object, String)
     *              @see CommonAnnotationBeanPostProcessor#autowireResource(BeanFactory, CommonAnnotationBeanPostProcessor.LookupElement, String)
         *
         *   4、实例化/属性填充的过程中-判断是否依赖其他类/bean-->先创建依赖的bean-->可能存在循环依赖-->
         *     4.1、构造方法-循环依赖，@Lazy核心{@link ContextAnnotationAutowireCandidateResolver#getLazyResolutionProxyIfNecessary(DependencyDescriptor, String)}
         *        @see AbstractBeanFactory#doGetBean
         *        //（A）bean使用有参构造参数实例化时-解析构造方法上的参数param
         *        4.1.1、{@link org.springframework.beans.factory.support.ConstructorResolver#createArgumentArray}
         *        //先判断requestingBeanName是否为懒加载，如果懒加载则返回一个代理对象
         *        //bean使用有参构造参数实例化时-寻找有没有这个bean，没有就创建（param参数）bean-最后来到这里-getBean(param)
         *        4.1.2、{@link DependencyDescriptor#resolveCandidate(String, Class, BeanFactory)}
         *        4.1.3、调用beanFactory.getBean(param)方法，如果已经有这个bean，则返回
         *        4.1.4、如果没有这个（param）bean，则创建又走bean的生命周期
         *        4.1.5、如果发现（param）也依赖（A），那凉凉，（A）和（param）循环依赖都无法实例化，报错
         *        4.1.6、加个@Lazy可以解决构造方法循环依赖，如果不是构造方法，那么@Lazy要加到参数中才能效果
         *     4.2、@DependsOn-循环依赖
         *        @see DependsOnConfig
         *        4.2.1、@DependsOn这个注解只关注了这个注解的值，和一般的感觉不一样，
         *              产生循环依赖时，根本没有走到实例化那一步
         *        {@link AbstractBeanFactory#doGetBean}
         *        {@link DefaultSingletonBeanRegistry#isDependent(String, String)}
         *        4.2.2、加@Lazy也不能解决DependsOn循环依赖，单方面加@Lazy，启动时直接报错
         *        4.2.3、双方加@Lazy，只是启动时不报错，真正使用的时候会报错
         *        4.2.4、，如果不是构造方法，那么@Lazy要加到参数中才能效果-解决循环依赖
         *     4.3、属性级别循环依赖{@link AutowiredServiceA}
         *        4.3.1、为循环依赖创建早期对象-解决循环依赖关键
         *          @see AbstractAutowireCapableBeanFactory#getEarlyBeanReference(String, RootBeanDefinition, Object)
         *        4.3.2、也可以用@Lazy解决
         *        4.3.3、（未涉及AOP）普通的循环依赖一般二级缓存可以解决，不涉及代理，在三级缓存中会涉及是否进行代理判断
         *          4.3.3.1、一级缓存：单例池，二级缓存：早期对象（未完整的bean），三级缓存：一段逻辑，是否要进行代理或AOP代理
         *        4.3.3、填充/设置属性（如果A<--->B）
         *          4.3.3.1、先创建A实例（实例化的构造方法会缓存起来的），设置A正在创建，填充属性时，发现依赖B，
         *              解析依赖{@link DefaultListableBeanFactory#doResolveDependency(DependencyDescriptor, String, Set, TypeConverter)}
         *          4.3.3.2、然后创建B实例，设置B正在创建，填充属性时，发现依赖A，这时单例池中，没有A和B，将实例B（早期对象）存入三级（一段逻辑）缓存
         *          4.3.3.3、然后又去准备创建A，此时缓存中已经存在A的实例构造方法，A实例化，发现A在创建中，将实例A（早期对象）存入三级（一段逻辑）缓存
         *          4.3.3.4、发现A依赖B，去单项池获取B，获取不到，去二级缓存获取B获取不到，然后看看第三级缓存，三级缓存就有了
         *          4.3.3.5、执行三级缓存逻辑，如果要进行代理或AOP，则返回代理对象；如果不用进行代理，则返回B早期对象，B早期对象存入二级早期对象缓存，删除B三级缓存
         *          4.3.3.6、将返回的B早期/代理对象进行属性填充（然后发现B依赖A，去单项池获取A，获取不到，去二级缓存获取A获取不到，然后看看第三级缓存，三级缓存就有了）
         *          4.3.3.7、执行三级缓存逻辑，如果要进行代理或AOP，则返回代理对象；如果不用进行代理，则返回A早期对象，A早期对象存入二级早期对象缓存，删除A三级缓存
         *   重点    4.3.3.8、返回A的早期或代理对象，因为已经获取到A的早期/代理对象，所以就不会再去解析A了，所以跳出循环依赖了
         *          4.3.3.9、将返回的A的早期或代理对象设置给B，B属性填充完毕
         *              @see AutowiredAnnotationBeanPostProcessor.AutowiredFieldElement#inject(Object, String, PropertyValues)
         *          4.3.3.10、然后开始B的初始化initializeBean方法
         *              @see AbstractAutowireCapableBeanFactory#initializeBean(String, Object, RootBeanDefinition)
         *          4.3.3.11、如果是普通bean对象即没有出现循环依赖的，如果需要进行AOP，那么会在初始化后调用初始化后方法，实现AOP返回代理对象
         *                    如果bean已经是代理对象或AOP代理对象，则不会进行AOP了，因为在执行第三级缓存的方法时，已经进行过代理或提前AOP处理
         *                  @see AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)
         *          4.3.3.12、B的bean创建流程完成，此时B的早期对象就已经设置为完整的bean对象了，创建将B的bean存入单例池中，删除二级缓存中的B早期对象
         *          4.3.3.13、B创建bean完成，返回A的（填充属性流程）把B属性设置值，A再走初始化方法，bean创建流程走完，把完整A对象设置到单例池中
         *          ============循环依赖解决，A和B都成功创建Bean
         *          4.3.3.14、可能会问，B的创建Bean流程走完，那A都没有创建完成，怎么能是完整的bean了呢？
         *            答：因为B中的属性A和A是存在引用关系的，但后续A创建bean的流程走完，那A为完整的bean后，
         *               那B中的A属性指向自然就是完整的bean对象了，而B成为完整的bean，那么同理A中的B属性指向自然就是完整的bean对象了
         *
         *   5、一些Aware回调-->
         *      @see AbstractAutowireCapableBeanFactory#invokeAwareMethods(String, Object)
         *   6、Bean初始化前-->
         *      @see AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsBeforeInitialization(Object, String)
         *      其他也会调用@PostConstruct注解的方法
         *   7、initializeBean初始化回调-->
         *      @see InitializeServiceA#afterPropertiesSet()
         *   8、初始化方法-->
         *      @see AbstractAutowireCapableBeanFactory#invokeInitMethods(String, Object, RootBeanDefinition)
         *      如果指定了init方法则会调用，可以在bean定义中设置
         *   9、Bean初始化后（正常情况AOP）-->
         *      @see AbstractAutowireCapableBeanFactory#applyBeanPostProcessorsAfterInitialization(Object, String)
         *      @see AbstractAutoProxyCreator#postProcessAfterInitialization(Object, String)
         *     9.1、先看看有没有AOP配置，如果有再看看这个bean是否需要AOP，查看类上的方法有哪个符合切点规则 {@link AopAspect#aopCut()}
         *     9.2、符合切点规则（一个AOP切面/配置类可以多个切点），看看切点应用于哪种（前置/后置/环绕等）通知（通知也可以同时配置多个切点）
         *     9.3、方法符合切点规则，并且切点有被（前置/后置/环绕等）通知应用，那么加入一个默认的通知，默认的通知里面加入一个拦截器
         *          @see AspectJAwareAdvisorAutoProxyCreator#extendAdvisors(List)
         *          默认的通知中加入了一个拦截器，拦截器用于后续AOP的执行
         *          @see ExposeInvocationInterceptor#ADVISOR
         *          AOP最后调用/实现
         *          @see ExposeInvocationInterceptor#invoke(MethodInvocation)
         *     9.4、通知排序
         *          @see AbstractAdvisorAutoProxyCreator#sortAdvisors(List)
         * 重要 9.5、设置/构造通知，创建代理对象-返回代理对象
         *          @see AbstractAutoProxyCreator#createProxy(Class, String, Object[], TargetSource)
         *          @see ProxyFactory#getProxy(java.lang.ClassLoader)
         * 重要     设置AOP回调拦截器{@link  org.springframework.aop.framework.CglibAopProxy#getCallbacks(Class)}
		 * 		   AOP默认回调{@link org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)}
         *     9.6、AOP通知执行顺序，可以测试{@link AopAspect}，单切面（类）中，加入@Order(3)不影响通知执行顺序
         *          代理对象调用方法-->
         *          默认的通知-->
         *              @see ExposeInvocationInterceptor#invoke(MethodInvocation)
         *  重要        {@link ReflectiveMethodInvocation#proceed()}
         *          环绕-->调用环绕通知，在环绕通知中，调用joinPoint.proceed()，通知重新走入循环/递归调用流程中，调用下一通知
         *              @see ExposeInvocationInterceptor#invoke(MethodInvocation)
         *          前置-->调用环绕通知，递归调用下一通知
         *              @see MethodBeforeAdviceInterceptor#invoke(MethodInvocation)
         *          后置-->递归调用下一通知，但后置通知调用会放到finally{}代码块中，所以最后无论是否发生异常都会调用
         *              @see AspectJAfterAdvice#invoke(MethodInvocation)
         *          返回-->递归调用下一通知，再调用返回通知，所以如果发生异常，则返回通知不调用
         *              @see AfterReturningAdviceInterceptor#invoke(MethodInvocation)
         *          异常-->调用目标方法，try-catch，如果发生异常，会调用异常通知
         *              @see AspectJAfterThrowingAdvice#invoke(MethodInvocation)
         *              发生异常时：
         *              @see AbstractAspectJAdvice#invokeAdviceMethod(JoinPointMatch, Object, Throwable)
         *          目标方法-->
         *              @see org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation#invokeJoinpoint()
         *          如果调用没有发生异常
         *              目标方法-->返回通知-->后置通知-->后环绕通知-->流程完毕
         *          如果调用发生异常
         *              目标方法异常-->异常通知-->后置通知-->抛出异常
         *   10、Spring事务（回调和设置回调详见：9.5）
		 *   	AOP默认回调{@link org.springframework.aop.framework.CglibAopProxy.DynamicAdvisedInterceptor#intercept(Object, Method, Object[], MethodProxy)}
		 *		{@link ReflectiveMethodInvocation#proceed()}
         *      真正处理事务入口 {@link TransactionInterceptor#invoke(MethodInvocation)}
		 *      事务时间是否过期{@link ResourceHolderSupport#getTimeToLiveInSeconds()}
         *      事务提交{@link TransactionAspectSupport#commitTransactionAfterReturning(TransactionAspectSupport.TransactionInfo)}
         *      10.1、总结{@link TransactionalService#add()}
         *          如果后续不需要执行SQL了，并且后续的程序没有报错/发生异常，那么就是存在卡顿或长时间处理不完
         * 		    那么也是一直在事务中的，设置了超期时间，也没有用的，等后续程序执行完也一样会提交事务
         *          如果有其他事务在等待这个事务，那得等这个事务释放后才能执行
         *         如果后续还有SQL要执行，那么超期时间的就有效了，因为执行SQL需要先获取数据库连接，在看看判断超期时间
         *   10、注册销毁方法-->
         *   11、加入单例池中-->
         *   12、容器销毁-->
         *      @see AbstractApplicationContext#close
         *   13、销毁前调用bean销毁方法-->
         *      @see InitializeServiceA#end()
         *   14、bean销毁
         */

        /**
         * AOP/事务使用
         *   1、引入依赖-->写上@EnableAspectJAutoProxy注解-->
         *   2、启动扫描beanDefinition时import对应的注册AnnotationAwareAspectJAutoProxyCreator（bean的后置处理器）
         */
    }
}
