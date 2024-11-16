package com.af.service;

import com.af.aop.AopAnnotate;
import org.springframework.beans.factory.annotation.Autowired;

public class HelloService {

    @Autowired
    private OrderService orderService;

    public HelloService(){
        System.out.println("调用HelloService无参构造方法");
    }

    @AopAnnotate
    public void HelloServiceTest(String str) throws Exception {
        System.out.println("调用HelloServiceTest方法,orderService = "+orderService+",Str="+str);
//        throw new Exception("HelloServiceTest发生异常咯");
    }

    @AopAnnotate
    public void HelloServiceTest2(){
        System.out.println("调用HelloServiceTest2方法,orderService = "+orderService);
    }

    public void HelloServiceTest3(){
        System.out.println("调用HelloServiceTest3方法,orderService = "+orderService);
    }
}
