package com.af.factoryBean;

import com.af.cglib.TestCGLib;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

@Component
public class FactoryBeanTest01 implements SmartFactoryBean<MyMapper> {

    @Autowired
    private MyMapper myMapper;

	@Override
	public MyMapper getObject() throws Exception {
		return (MyMapper) new TestCGLib().getInstance(MyMapper.class);
	}

	@Override
	public Class<?> getObjectType() {
		return MyMapper.class;
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

    public static void main(String[] args) {
        MyMapper instance = (MyMapper) new TestCGLib().getInstance(MyMapper.class);
        String str = instance.test01("MyMapper");
        System.out.println(str);

        MyMapperImpl impl = (MyMapperImpl) new TestCGLib().getInstance(MyMapperImpl.class);
        String str2 = impl.test01("123");
        System.out.println(str2);
    }

}
