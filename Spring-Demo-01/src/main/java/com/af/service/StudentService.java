package com.af.service;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * 学生Service
 */
public class StudentService {

    @Autowired
    private OrderService orderService;

    public StudentService(){
        System.out.println("StudentService无参构造方法被调用了");
    }

    public StudentService(UserService userService1,UserService userService2){
        System.out.println("StudentService指定的构造方法被调用了,userService1 = "+userService1 + ",userService2 = " + userService2);
    }

    public void studentServiceTest01(){
        System.out.println("调用StudentServiceTest01, orderService = "+orderService);
    }


}
