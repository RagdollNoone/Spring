package com.authserver.service;

import com.authserver.bean.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    SysUser getByUsername(String username);
}
