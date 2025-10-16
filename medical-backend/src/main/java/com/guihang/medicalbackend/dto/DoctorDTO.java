package com.guihang.medicalbackend.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "用于封装医生信息数据")
public class DoctorDTO {


    @Schema(title = "医生id")
    private Long id;

    @Schema(title = "医生姓名")
    private String name;

    @Schema(title = "医生年龄")
    private Integer age;

    @Schema(title = "医生性别")
    private Integer sex;

    @Schema(title = "身份等级")
    private Integer levelId;

    @Schema(title = "电话号码")
    private String phone;

    @Schema(title = "类型id")
    private Integer typeId;

    @Schema(title = "医院")
    private String hospital;

    @Schema(title = "账号id")
    private Integer accountId;


}
