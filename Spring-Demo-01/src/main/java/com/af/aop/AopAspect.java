package com.af.aop;
 
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
 
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
     * 前置通知，在方法运行前输出
     *
     * @param joinPoint 连接点
     */
    @Before("aopCut()")
    public void before(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP开始拦截, 当前拦截的方法名: " + method.getName());
 
        System.out.println(Arrays.toString(joinPoint.getArgs()));
    }
 
    /**
     * 后置通知，在方法运行后输出
     *
     * @param joinPoint 连接点
     */
    @After("aopCut()")
    public void after(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP执行的方法 :" + method.getName() + " 执行完了");
    }
 
    /**
     * 环绕通知，在方法运行前和后分别输出
     * joinPoint.proceed() 表示方法运行
     *
     * @param joinPoint 连接点
     */
    @Around("aopCut()")
    public Object testCutAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("注解方式AOP拦截开始进入环绕通知.......");
        Object proceed = joinPoint.proceed();
        System.out.println("准备退出环绕......");
        return proceed;
    }
 
    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * AfterReturning   获取到返回值后输出
     *
     * @param joinPoint 连接点
     * @param result    返回结果
     */
    @AfterReturning(value = "aopCut()", returning = "result")
    public void afterReturn(JoinPoint joinPoint, Object result) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP拦截的方法执行成功, 进入返回通知拦截, 方法名为: " + method.getName() + ", 返回结果为: " + result.toString());
    }
 
    /**
     * returning属性指定连接点方法返回的结果放置在result变量中
     * AfterThrowing    抛出异常后数据
     *
     * @param joinPoint 连接点
     * @param e         异常结果
     */
    @AfterThrowing(value = "aopCut()", throwing = "e")
    public void afterThrow(JoinPoint joinPoint, Exception e) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        System.out.println("注解方式AOP进入方法异常拦截, 方法名为: " + method.getName() + ", 异常信息为: " + e.getMessage());
    }
}