package com.guihang.medicalbackend.service.impl;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.mapper.PermissionMapper;
import com.guihang.medicalbackend.pojo.Permission;
import com.guihang.medicalbackend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionMapper permissionMapper;

    @Override
    public JSONResult getPermissionByRoleName(String roleName) {
        System.out.println("================================" + roleName);
        if (roleName == null || "".equals(roleName)) {
            return new JSONResult(201, "没有角色名称", null);
        }

        List<Permission> permissions = permissionMapper.getPermissionByRoleName(roleName);
        if (permissions == null || permissions.isEmpty()) {
            return new JSONResult(202, "查询失败", null);
        } else {
            return new JSONResult(200, "查询成功", permissions);
        }

    }


}
