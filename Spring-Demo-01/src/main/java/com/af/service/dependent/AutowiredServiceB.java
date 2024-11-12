package com.af.service.dependent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 测试循环依赖-属性循环依赖
 * A<--->B
 */
@Component
public class AutowiredServiceB {

    @Autowired(required = true)
    private AutowiredServiceA autowiredServiceA;
}
