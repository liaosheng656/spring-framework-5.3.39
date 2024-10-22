package com.af.cglib;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 代理类
 */
public class TestCGLib implements MethodInterceptor {
 
    public Object getInstance(Class cla) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(cla);
        // 回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }
 
    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Before method invocation: " + method.getName());

        //这里写死，其实MyMapper是接口，接口代理，因为没有
        if(args[0].toString().equals("MyMapper")){
            return "调用成功";
        }

        // 调用原始方法
        Object result = methodProxy.invokeSuper(obj, args);
        System.out.println("After method invocation: " + method.getName());
        System.out.println(result.toString());
        return result;
    }
}