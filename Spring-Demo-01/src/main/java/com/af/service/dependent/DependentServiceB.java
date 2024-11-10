package com.af.service.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DependentServiceB {

//    @Autowired
//    private DependentServiceA dependentServiceA;

    @Autowired
    public DependentServiceB (DependentServiceA dependentServiceA){

    }
}
