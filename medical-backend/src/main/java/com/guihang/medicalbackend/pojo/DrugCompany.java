package com.guihang.medicalbackend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("drugcompany")
public class DrugCompany extends  BasePojo {
    @TableId(type = IdType.AUTO)
    private Long companyId;//公司id
    private String companyName;//公司名
    private String companyPhone;//公司电话
}
