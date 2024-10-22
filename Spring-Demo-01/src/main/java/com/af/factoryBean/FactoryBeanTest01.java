package com.af.factoryBean;

import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class FactoryBeanTest01 implements SmartFactoryBean {
	@Override
	public Object getObject() throws Exception {
		return null;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	/**
	 * 是否是原型
	 * @return
	 */
	@Override
	public boolean isPrototype() {
		return false;
	}

	/**
	 * 是否要马上初始化
	 * @return
	 */
	@Override
	public boolean isEagerInit() {
		return false;
	}
}
