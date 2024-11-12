package com.af.service.dependent;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


    /**
     * 这种方式也和构造方法循环依赖一致
     * 这种只能在参数上加@Lazy注解，才能解决循环依赖
     * @param dependentServiceBa2
     * @return
     */
    @Bean
    public DependentServiceA dependentServiceAa2(
//            @Qualifier("dependentServiceB")
            DependentServiceB dependentServiceBa2)
    {

        return new DependentServiceA();
    }

    @Order(6)
    @Bean
    public DependentServiceB dependentServiceBa2(@Lazy DependentServiceA dependentServiceAa2){
        return new DependentServiceB();
    }
}
