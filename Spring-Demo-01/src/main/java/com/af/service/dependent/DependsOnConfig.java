package com.af.service.dependent;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.Order;

@Configuration
public class DependsOnConfig {

    /**
     * 加@Lazy也不能解决DependsOn循环依赖，
     * 单方面加@Lazy，启动时直接报错
     * 双方加@Lazy，只是启动时不报错，真正使用的时候会报错
     * @return
     */
//    @DependsOn("dependentServiceBa")
//    @Lazy
    @Bean
    public DependentServiceA dependentServiceAa(){

        return new DependentServiceA();
    }

    @Order(5)
    @DependsOn("dependentServiceAa")
    @Lazy
    @Bean
    public DependentServiceB dependentServiceBa(){
        return new DependentServiceB();
    }
}
