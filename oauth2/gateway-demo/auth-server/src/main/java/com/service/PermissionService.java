package com.service;

import com.bean.SysPermission;
import java.util.List;

public interface PermissionService  {
    List<SysPermission> selectByUserId(Long userId);
}
