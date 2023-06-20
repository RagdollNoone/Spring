package com.service.impl;

import com.bean.SysPermission;
import com.mapper.PermissionMapper;
import com.service.PermissionService;
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
