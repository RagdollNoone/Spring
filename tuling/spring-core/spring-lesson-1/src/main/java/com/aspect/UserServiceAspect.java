package com.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserServiceAspect {
    @Before("execution(public void com.service.UserService.test())")
    public void userServiceTestBefore(JoinPoint joinPoint) {
        // joinPoint.getTarget() --> 真正的userService 而不是代理对象
        System.out.println("[UserServiceAspect][userServiceTestBefore]start");
    }
}
