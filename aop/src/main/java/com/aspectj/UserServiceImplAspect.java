package com.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class UserServiceImplAspect {
    @Before("execution(public void com.aspectj.UserServiceImpl.test())")
    public void userServiceTestBefore(JoinPoint joinPoint) {
        // joinPoint.getTarget() --> 真正的userService 而不是代理对象
        System.out.println("[UserServiceImplAspect][userServiceTestBefore]");
    }
}
