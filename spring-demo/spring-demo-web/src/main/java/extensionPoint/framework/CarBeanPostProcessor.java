package extensionPoint.framework;

import extensionPoint.api.Car;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

// 拦截order 3
@Component
public class CarBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        // 每一个bean都会通过这个BeanPostProcessor
//        System.out.println("do CarBeanPostProcessor ...");

        if (bean instanceof Car && beanName.equals("myCar")) {
            Car car = (Car) bean;
            car.setName("benz");
        }

        return bean;
    }
}
