package com.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AdminUserService {
    @Value("michael")
    private AdminUser adminUser;

    public void printAdminUserName() {
        System.out.println("[AdminUserService][printAdminUserName]adminUserName: " + adminUser.getName());
    }
}
