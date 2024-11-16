package com.af.aop;
 
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.aspectj.weaver.tools.JoinPointMatch;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.aspectj.AspectJAfterThrowingAdvice;
import org.springframework.aop.aspectj.AspectJAroundAdvice;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;
import org.springframework.aop.framework.adapter.MethodBeforeAdviceInterceptor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
 
/**
 * @author szh
 */
@Aspect
@Component
public class AopAspect {
    /**
     * 定义了一个切点
     * 这里的路径填自定义注解的全路径
     * @Pointcut(value = "execution(* com.example.shardingsphere.controller.*.*(..))")
     */
    @Pointcut("@annotation(com.af.aop.AopAnnotate)")
    public void aopCut() {
 
    }


    /**
     * 环绕通知，在方法运行前和后分别输出
     * joinPoint.proceed() 表示方法运行
     *
     * @param joinPoint 连接点
     * @see AspectJAroundAdvice#invoke(MethodInvocation)
     */
    @Around("aopCut()")
    @Order(1)
    public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        //请求参数
        Object[] args = joinPoint.getArgs();
        //这个可以获取到很多东西
        Signature signature = joinPoint.getSignature();
        //获取到方法
        MethodSignature methodSignature = (MethodSignature)signature;
        Method method = methodSignature.getMethod();
        //目标类名
        String name = method.getDeclaringClass().getName();
        String methodName = method.getName();
        //方法有哪些注解
        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
        if(declaredAnnotations != null && declaredAnnotations.length > 0){
            for (Annotation annotation:declaredAnnotations) {
                System.out.println("annotationsName="+annotation.annotationType().getName());
            }
        }
        System.out.println("name="+name+",methodName="+methodName);
        if(args != null && args.length > 0){
            for (Object obj:args) {
                System.out.println("testCutAround，被调用方法参数有="+obj);
            }

        }
        System.out.println("注解方式AOP拦截开始进入Around环绕通知.......");
        //这里是关键，调了之后，通知重新走入循环调用流程中
        /**
         * @see ReflectiveMethodInvocation#proceed()
         */
        Object proceed = joinPoint.proceed();
        System.out.println("准备退出Around环绕......");
        return proceed;
    }

    /**
     * 前置通知，在方法运行前输出
     *
     * @param joinPoint 连接点
     * @see MethodBeforeAdviceInterceptor#invoke(MethodInvocation)
     */
    @Order(3)
    @Before("aopCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP开始拦截, before拦截的方法名: " + method.getName());
 
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
 
    /**
     * 后置通知，在方法运行后输出
     *
     * @param joinPoint 连接点
     * @see AspectJAfterAdvice#invoke(MethodInvocation)
     */
    @After("aopCut()")
    @Order(2)
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP执行的方法 after拦截的方法名:" + method.getName() + " 执行完了");
    }

 
    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * AfterReturning   获取到返回值后输出
     *
     * @param joinPoint 连接点
     * @param result    返回结果
     * @see AfterReturningAdviceInterceptor#invoke(MethodInvocation)
     */
    @AfterReturning(value = "aopCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP拦截的方法执行成功, 进入返回afterReturn通知拦截, 方法名为: " + method.getName() + ", 返回结果为: " + result+"");
    }
 
    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * AfterThrowing    抛出异常后数据
     *
     * @param joinPoint 连接点
     * @param e 异常结果
     * @see AspectJAfterThrowingAdvice#invoke(MethodInvocation)
     * 发生异常时：
     * @see AbstractAspectJAdvice#invokeAdviceMethod(JoinPointMatch, Object, Throwable)
     * 递归最后调目标方法，调完目标方法，然后慢慢返回
     * @see org.springframework.aop.framework.CglibAopProxy.CglibMethodInvocation#invokeJoinpoint()
     */
    @AfterThrowing(value = "aopCut()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP进入方法异常拦截, afterThrow方法名为: " + method.getName() + ", 异常信息为: " + e.getMessage());
    }
}