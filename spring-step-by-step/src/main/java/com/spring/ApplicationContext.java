package com.spring;

import com.spring.inter.BeanPostProcessor;
import com.spring.inter.InitializingBean;
import com.spring.annotation.Autowired;
import com.spring.annotation.Component;
import com.spring.annotation.ComponentScan;
import com.spring.annotation.Scope;
import com.spring.bean.BeanDefinition;
import com.spring.inter.BeanNameAware;

import java.beans.Introspector;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.*;

public class ApplicationContext {
    private final Class<?> configClass; // 配置class
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>(); // beanDefinition缓存池
    private final Map<String, Object> singletonObjects = new HashMap<>(); // 单例池
    private final List<BeanPostProcessor> beanPostProcessorList = new ArrayList<>(); // 处理器列表

    public ApplicationContext(Class<?> configClass) {
        this.configClass = configClass;

        // 扫描package
        scan();

        // 创建所有单例bean
        createAllSingletonBean();
    }

    public Object getBean(String beanName) {
        assert beanDefinitionMap.containsKey(beanName);

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if ("singleton".equals(beanDefinition.getScope())) {
            Object singletonBean = singletonObjects.get(beanName);
            if (null == singletonBean) {
                singletonBean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, singletonBean);
            }
            return singletonBean;
        } else {
            return createBean(beanName, beanDefinition); // 创建prototypeBean
        }
    }

    private void scan() {
        assert configClass != null;

        // 扫描所有ComponentScan注解的对象
        if (configClass.isAnnotationPresent(ComponentScan.class)) {
            ComponentScan componentScanAnnotation = (ComponentScan) configClass.getAnnotation(ComponentScan.class);
            String path = componentScanAnnotation.value();
            path = path.replace(".", "/"); // com/demo/service

            ClassLoader classLoader = ApplicationContext.class.getClassLoader();
            URL resource = classLoader.getResource(path);
            assert resource != null;
            File file = new File(resource.getFile());

            if (file.isDirectory()) {
                for (File f : Objects.requireNonNull(file.listFiles())) {
                    // 获取class文件名
                    String absolutePath = f.getAbsolutePath();
                    absolutePath = absolutePath.substring(absolutePath.indexOf("com"), absolutePath.indexOf(".class"));
                    absolutePath = absolutePath.replace("\\", ".");

                    try {
                        // 加载class文件
                        Class<?> clazz = classLoader.loadClass(absolutePath);

                        // 创建BeanDefinition
                        if (clazz.isAnnotationPresent(Component.class)) {
                            Component componentAnnotation = clazz.getAnnotation(Component.class);
                            String beanName = componentAnnotation.value();

                            if ("".equals(beanName)) {
                                beanName = Introspector.decapitalize(clazz.getSimpleName());
                            }

                            BeanDefinition beanDefinition = new BeanDefinition();
                            beanDefinition.setType(clazz);

                            if (clazz.isAnnotationPresent(Scope.class)) {
                                Scope scopeAnnotation = clazz.getAnnotation(Scope.class);
                                String value = scopeAnnotation.value();
                                beanDefinition.setScope(value);
                            } else {
                                beanDefinition.setScope("singleton");
                            }

                            beanDefinitionMap.put(beanName, beanDefinition);
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    private Object createBean(String beanName, BeanDefinition beanDefinition) {
        Class<?> clazz = beanDefinition.getType();

        Object instance = null;
        try {
            // 创建对象
            instance = clazz.getConstructor().newInstance();

            // 依赖注入
            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(Autowired.class)) {
                    field.setAccessible(true);
                    field.set(instance, getBean(field.getName()));
                }
            }

            // Aware处理
            if (instance instanceof BeanNameAware) {
                ((BeanNameAware) instance).setName(beanName);
            }

            // 前置处理器
            for (BeanPostProcessor processor : beanPostProcessorList) {
                instance = processor.postProcessBeforeInitialization(instance, beanName);
            }

            // 初始化逻辑
            if (instance instanceof InitializingBean) {
                ((InitializingBean) instance).afterPropertiesSet();
            }

            // 后置处理器
            for (BeanPostProcessor processor : beanPostProcessorList) {
                instance = processor.postProcessAfterInitialization(instance, beanName);
            }

            // 类型判断后 添加BeanPostProcessor
            if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                beanPostProcessorList.add((BeanPostProcessor)instance);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private void createAllSingletonBean() {
        for (Map.Entry<String, BeanDefinition> entry : beanDefinitionMap.entrySet()) {
            String beanName = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            if ("singleton".equals(beanDefinition.getScope())) {
                Object bean = createBean(beanName, beanDefinition);
                singletonObjects.put(beanName, bean);
            }
        }
    }
}