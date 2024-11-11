package com.af.service.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class DependentServiceA {


//    @Autowired
//    private DependentServiceB dependentServiceB;

    public DependentServiceA (){

    }

    /**
     * 构造方法循环依赖
     * 加个@Lazy可以解决循环依赖
     */
    @Lazy
    @Autowired
    public DependentServiceA (DependentServiceB dependentServiceB){

    }
}
