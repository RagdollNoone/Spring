package com.component;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class UserServiceCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        try {
//            context.getClassLoader().loadClass("com.User"); // 加载错误路径 导致当前condition返回false
            context.getClassLoader().loadClass("com.component.User");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
