package com.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserFactoryBean implements SmartFactoryBean<User> {
    @Override
    public boolean isEagerInit() {
        return true;
    }

    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
