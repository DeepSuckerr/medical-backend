package com.guihang.medicalbackend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "用户登录封装数据")
public class AccountDTO implements Serializable {
    @Schema(title = "用户id")
    private Long id;

    @Schema(title = "用户真实姓名")
    private String realName;

    @Schema(title = "用户名")
    private String uname;

    @Schema(title = "用户密码")
    private String pwd;

    @Schema(title = "用户电话")
    private String phoneNumber;

    @Schema(title = "用户角色")
    private String uType;

    @Schema(title = "用户修改时间")
    protected Date updateTime;

    @Schema(title = "用户创建时间")
    protected Date createTime;

    @Schema(title = "token值")
    private String token;
}
