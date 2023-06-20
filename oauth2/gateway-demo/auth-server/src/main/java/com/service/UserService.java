package com.service;

import com.bean.SysUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    SysUser getByUsername(String username);
}
