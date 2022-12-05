package com.component;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(UserServiceCondition.class)
public class UserService {
}
