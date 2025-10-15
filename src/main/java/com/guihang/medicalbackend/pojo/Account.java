package com.guihang.medicalbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@TableName("account")
public class Account extends BasePojo implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String realName;
    private String uname;
    private String pwd;
    private String phoneNumber;
    private String utype;
    private String salt;
}
