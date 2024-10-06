package com.af.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(4)
@Component
public class OrderService {

	@Autowired
	private UserService userService;

    public OrderService(){
        System.out.println("调用OrderService无参构造方法");
    }

    public OrderService(UserService userService){
        System.out.println("调用OrderService有一个构造方法的OrderService, userService="+userService);
    }

    public void orderServiceTest01(){
        System.out.println("init");
        System.out.println("init初始化方法---执行orderServiceTest01");
    }
}
