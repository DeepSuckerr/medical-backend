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
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor")
@Data
public class Doctor extends BasePojo implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long id;
    private String name;
    private Integer age;
    private Integer sex;
    private Integer levelId;
    private String phone;
    private Integer typeId;
    private String hospital;
    private Integer accountId;





}
