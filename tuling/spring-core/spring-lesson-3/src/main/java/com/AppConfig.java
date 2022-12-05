package com;

import org.springframework.context.annotation.ComponentScan;

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
