package com.af.Import1;

import com.af.service.StudentService;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 配合@import注解使用
 */
public class ImportBeanDefinitionRegistrarTest01 implements ImportBeanDefinitionRegistrar {

    /**
     *
     * @param importingClassMetadata annotation metadata of the importing class
     * @param registry current bean definition registry
     * @param importBeanNameGenerator the bean name generator strategy for imported beans:
     * {@link ConfigurationClassPostProcessor#IMPORT_BEAN_NAME_GENERATOR} by default, or a
     * user-provided one if {@link ConfigurationClassPostProcessor#setBeanNameGenerator}
     * has been set. In the latter case, the passed-in strategy will be the same used for
     * component scanning in the containing application context (otherwise, the default
     * component-scan naming strategy is {@link AnnotationBeanNameGenerator#INSTANCE}).
     */
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry,
                                        BeanNameGenerator importBeanNameGenerator){

        System.out.println("回调registerBeanDefinitions，可以注册BeanDefinition," +
                "importingClassMetadata = " +importingClassMetadata.getClassName());
    }

    /**
     * 这个是不会调的，因为处理这个逻辑，没把这种情况当做配置类处理
     * @return
     */
    @Bean("studentService04")
    public StudentService studentService01(){
        return new StudentService();
    }
}
