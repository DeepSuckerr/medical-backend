package com.guihang.medicalbackend.service;

import com.guihang.medicalbackend.commons.JSONResult;

public interface PermissionService {
    JSONResult getPermissionByRoleName(String roleName);
}
