package com.af.config;

import com.af.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 *
 * classpath 和 classpath*的 区别
 *
 * classpath：只会到你指定的class路径中查找找文件;
 * classpath*：不仅包含class路径，还包括jar文件中(class路径)进行查找.
 *
 * 1:未打包前classpath就是项目结构中的src文件夹。
 * 2:经过maven打包以后你会在idea中看见一份target文件夹，这里边的classes就是classpath。
 */
@Configuration
@ImportResource(locations = {"classpath:beans.xml"},reader = XmlBeanDefinitionReader.class)
public class ImportResourceConfig {

    @Autowired
    private OrderService orderService;

    public ImportResourceConfig(){
        System.out.println("ImportResourceConfig无参构造方法调用");
    }

    public void importResourceConfigTest(){
        System.out.println("importResourceConfigTest调用,orderService = "+orderService);
    }
}
