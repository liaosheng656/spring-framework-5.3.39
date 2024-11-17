package com.af.service;

import com.af.factoryBean.MyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Order(2)
@Component
public class UserService {

    @Autowired
    private MyMapper myMapper;

    @Resource
    private MyMapper myMapper2;
}
