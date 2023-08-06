package extensionPoint.framework;

import extensionPoint.api.Car;
import extensionPoint.component.Tyre;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.rootBeanDefinition;

@Component
public class CatBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Bean
    public Tyre tyre() {
        Tyre tyre = new Tyre();
        tyre.setColor("black");
        return tyre;
    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 注册beanDefinition
        BeanDefinition bd = new GenericBeanDefinition();
        bd.setBeanClassName("extensionPoint.api.Car");
        registry.registerBeanDefinition("car", bd);

        BeanDefinitionBuilder builder = rootBeanDefinition(Car.class);
        builder.addPropertyValue("name", "bmw");
        builder.addPropertyReference("tyre", "tyre");

        AbstractBeanDefinition enhanceBd = builder.getBeanDefinition();
        registry.registerBeanDefinition("myCar", enhanceBd);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
