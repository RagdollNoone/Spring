package extensionPoint.framework;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

// 拦截order 2
@Component
public class DriverBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 不好用 有很多非关联的
//        String[] beanNames = beanFactory.getBeanDefinitionNames();
//        for (String beanName : beanNames) {
//            if (beanName.startsWith(""))
//            System.out.println(beanName + " in current BeanFactory");
//        }

        BeanDefinition bd = beanFactory.getBeanDefinition("driver");
        MutablePropertyValues propertyValues = bd.getPropertyValues();
        propertyValues.add("name", "Michael");
    }
}
