package com.guihang.medicalbackend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guihang.medicalbackend.pojo.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {
    // 根据角色的名称获取该角色的所有权限
    List<Permission> getPermissionByRoleName(String roleName);
}
