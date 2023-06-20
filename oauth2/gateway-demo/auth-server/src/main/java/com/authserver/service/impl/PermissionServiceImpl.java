package com.authserver.service.impl;

import com.authserver.bean.SysPermission;
import com.authserver.mapper.PermissionMapper;
import com.authserver.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public List<SysPermission> selectByUserId(Long userId) {
        return permissionMapper.selectByUserId(userId);
    }
}
