package com.af.service.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(3)
@Component
public class DependentServiceA {

//    @Autowired
//    private DependentServiceB dependentServiceB;

    @Autowired
    public DependentServiceA (DependentServiceB dependentServiceB){

    }
}
