package com.af.service;

import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

    @Autowired
    private OrderService orderService;

    public HelloService(){
        System.out.println("调用HelloService无参构造方法");
    }

    public void HelloServiceTest(){
        System.out.println("调用HelloServiceTest方法,orderService = "+orderService);
    }
}
