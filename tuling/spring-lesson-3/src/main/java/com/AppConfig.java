package com;

import com.component.AdminUser;
import com.component.StringToAdminUserConverter;
import com.component.StringToAdminUserPropertyEditor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ConversionServiceFactoryBean;

import java.beans.PropertyEditor;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@ComponentScan("com")
public class AppConfig {

    // 字符串转类的配置方法 匹配test6
//    @Bean
//    public CustomEditorConfigurer customEditorConfigurer() {
//        CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
//        Map<Class<?>, Class<? extends PropertyEditor>> propertyEditorMap = new HashMap<>();
//
//        propertyEditorMap.put(AdminUser.class, StringToAdminUserPropertyEditor.class);
//        customEditorConfigurer.setCustomEditors(propertyEditorMap);
//
//        return customEditorConfigurer;
//    }


    // 字符串转类的配置方法 匹配test7
//    @Bean
//    public ConversionServiceFactoryBean conversionService() {
//        ConversionServiceFactoryBean conversionServiceFactoryBean = new ConversionServiceFactoryBean();
//        conversionServiceFactoryBean.setConverters(Collections.singleton(new StringToAdminUserConverter()));
//
//        return conversionServiceFactoryBean;
//    }
}
