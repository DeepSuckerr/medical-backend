package com.guihang.medicalbackend.controller;


import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;
import com.guihang.medicalbackend.pojo.Doctor;
import com.guihang.medicalbackend.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@Tag(name = "医生信息模块", description = "医生信息接口")
@CrossOrigin
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @PostMapping("/getDoctorAll")
    @Operation(summary = "获取医生信息", description = "获取医生信息接口")
    public JSONResult getDoctorAll(@RequestBody DoctorDTO doctorDTO){
        return doctorService.getAllDoctor(doctorDTO);
    }



}
