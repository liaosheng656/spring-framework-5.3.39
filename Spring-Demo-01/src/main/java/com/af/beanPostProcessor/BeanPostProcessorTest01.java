package com.af.beanPostProcessor;

import com.af.service.OrderService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class BeanPostProcessorTest01 implements BeanPostProcessor {

    /**
     * 每个bean初始化之前调用
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof OrderService){
            OrderService bean1 = (OrderService) bean;
            bean1.orderServiceTest01();

            return bean1;
        }
        return bean;
    }

    /**
     * 每个bean初始化之后调用
     * @param bean the new bean instance
     * @param beanName the name of the bean
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        return bean;
    }
}
