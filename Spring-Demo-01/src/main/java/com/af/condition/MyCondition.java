package com.af.condition;

import com.af.annotation.MyAnnotation;
import com.af.service.UserService;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.ResolvableType;
import org.springframework.core.annotation.MergedAnnotation;
import org.springframework.core.annotation.MergedAnnotations;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.StandardAnnotationMetadata;

import java.lang.annotation.Annotation;
import java.util.Map;
import java.util.Optional;

/**
 * 条件注入-自定义规则
 */
public class MyCondition implements Condition {

	/**
	 * 扫描的时候也会进来
	 * @param context the condition context
	 * @param metadata the metadata of the {@link org.springframework.core.type.AnnotationMetadata class}
	 * or {@link org.springframework.core.type.MethodMetadata method} being checked
	 * @return
	 */
	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {

		MergedAnnotations annotations = metadata.getAnnotations();
		if(annotations == null){
			return false;
		}
		//获取类的注解
//		if (metadata instanceof StandardAnnotationMetadata) {
//			//加载源配置类
//			return ((StandardAnnotationMetadata) metadata).getIntrospectedClass();
//		}
		//有没有@Bean方法
//		metadata.hasAnnotatedMethods(Bean.class.getName())
		String name = metadata.getClass().getName();
		System.out.println("MyCondition--matches---name = "+name);
		BeanDefinitionRegistry registry = context.getRegistry();
		ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
		String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
		for (String beanDefinitionName:beanDefinitionNames) {
			BeanDefinition beanDef = beanFactory.getBeanDefinition(beanDefinitionName);

			String className = beanDef.getBeanClassName();
			if (className == null || beanDef.getFactoryMethodName() != null) {
				continue;
			}
			if (!(beanDef instanceof AnnotatedBeanDefinition &&
					className.equals(((AnnotatedBeanDefinition) beanDef).getMetadata().getClassName()))) {
				continue;
			}
			// Can reuse the pre-parsed metadata from the given BeanDefinition...
			//获取目标类上的注解信息
			AnnotationMetadata metadata1 = ((AnnotatedBeanDefinition) beanDef).getMetadata();
			//查看目标类上是否有@Configuration注解
			Map<String, Object> config = metadata1.getAnnotationAttributes(MyAnnotation.class.getName());
			if(config == null){
				continue;
			}
			Object iniObj = config.get("init");
			if(iniObj == null){
				continue;
			}
			boolean init = (boolean) iniObj;
			if(init){
				//含有@MyAnnotation注解，并且init=true
				return true;
			}
		}
		return false;
	}
}
