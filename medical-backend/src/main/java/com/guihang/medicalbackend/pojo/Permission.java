package com.guihang.medicalbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

// 权限表
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("permission")
public class Permission implements Serializable {
    @TableId(type = IdType.AUTO)
    private Integer id; // 菜单id
    private Integer pid; // 菜单父id
    private String path; // 菜单路径
    private String name; // 菜单name
    private String component; // 菜单组件
    private Integer level; // 菜单级别
    private String title; // 菜单名称
    @TableField(exist = false) // 表中没有这个字段
    private List<Permission> children;// 子菜单
}
