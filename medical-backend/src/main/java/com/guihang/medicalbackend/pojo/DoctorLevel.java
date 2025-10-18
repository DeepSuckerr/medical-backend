package com.guihang.medicalbackend.pojo;


import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("doctor_level")
public class DoctorLevel {

    private Integer id;
    private String name;


}
