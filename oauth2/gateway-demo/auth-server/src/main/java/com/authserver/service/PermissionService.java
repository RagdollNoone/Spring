package com.authserver.service;

import com.authserver.bean.SysPermission;
import java.util.List;

public interface PermissionService  {
    List<SysPermission> selectByUserId(Long userId);
}
