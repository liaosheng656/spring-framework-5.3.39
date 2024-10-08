package com.af.Import1;

import com.af.annotation.MyImportAnnotation;
import com.af.service.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * ImportSelector测试
 */
public class ImportSelectorTest01 implements ImportSelector {

	@Override
	public String[] selectImports(AnnotationMetadata importingClassMetadata) {
		String className = importingClassMetadata.getClassName();
		System.out.println("自定义selectImports，className = "+className);
		String [] arr = new String[1];
		//查看className目标类上是否有MyAnnotation注解
		Map<String, Object> config = importingClassMetadata.getAnnotationAttributes(MyImportAnnotation.class.getName());
		if(config == null || config.get("init") == null || !(boolean) config.get("init")){
			arr[0] = "com.af.Import1.ImportInitFalse";
			return arr;
		}
		arr[0] = "com.af.Import1.ImportInitTrue";
		return arr;
	}

    /**
     * 这个也是不会调的，但是会导入的com.af.Import1.ImportInitTrue当做配置类处理
     * @return
     */
    @Bean("studentService03")
    public StudentService studentService01(){
        return new StudentService();
    }
}
