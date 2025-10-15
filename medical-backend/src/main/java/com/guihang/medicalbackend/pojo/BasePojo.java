package com.guihang.medicalbackend.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
// 父类 属性要被子类继承使用  属性不要使用私有
@Data
public class BasePojo implements Serializable {
    protected Date updateTime;
    protected Date createTime;
}
