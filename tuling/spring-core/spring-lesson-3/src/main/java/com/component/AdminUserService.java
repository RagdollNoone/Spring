package com.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminUserService {
    // test6 7 8时需要打开
//    @Value("michael")
    private AdminUser adminUser;

    public void printAdminUserName() {
        System.out.println("[AdminUserService][printAdminUserName]adminUserName: " + adminUser.getName());
    }
}
