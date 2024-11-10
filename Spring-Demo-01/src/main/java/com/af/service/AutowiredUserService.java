package com.af.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AutowiredUserService {

//    @Resource
//    private UserService userService;

    public AutowiredUserService(){
        System.out.println("AutowiredUserService无参构造参数");
    }

    //@Autowired(required = true)-bean实例化会执行这个构造方法
//    @Autowired(required = true)
    public AutowiredUserService(OrderService orderService){
        System.out.println("AutowiredUserService有1个参的构造参数，orderService="+orderService);
    }

    public AutowiredUserService(OrderService orderService,OrderService orderService2){
        System.out.println("AutowiredUserService有2个参的构造参数，orderService="+orderService);
    }

    public AutowiredUserService(OrderService orderService,OrderService orderService2,OrderService orderService3){
        System.out.println("AutowiredUserService有3个参的构造参数，orderService="+orderService);
    }


    public AutowiredUserService(OrderService orderService,OrderService orderService2,OrderService orderService3
            ,OrderService orderService4){
        System.out.println("AutowiredUserService有4个参的构造参数，orderService="+orderService);
    }
}
