package com.guihang.medicalbackend.controller;


import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;
import com.guihang.medicalbackend.pojo.Doctor;
import com.guihang.medicalbackend.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/doctor")
@Tag(name = "医生信息模块", description = "医生信息接口")
@CrossOrigin
public class DoctorController {

    @Autowired
    DoctorService doctorService;


    @GetMapping("/getDoctorAll")
    @Operation(summary = "获取医生信息", description = "获取医生信息接口")
    public JSONResult getDoctorAll(@RequestParam(name = "keyword", required = false) String keyword,
                                   @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                                   @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return doctorService.getAllDoctor(keyword, currentPage, pageSize);
    }

    @DeleteMapping("/deleteDoctor")
    @Operation(summary = "删除医生信息", description = "删除医生信息接口")
    public JSONResult deleteDoctor(@RequestParam(name = "id") Long id) {
        return doctorService.DeleteDoctorById(id);
    }

    @PostMapping("/addDoctor")
    @Operation(summary = "新增医生信息", description = "新增医生信息接口")
    public JSONResult addDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.addDoctor(doctorDTO);
    }

    @PostMapping("/updateDoctor")
    @Operation(summary = "修改医生信息", description = "修改医生信息接口")
    public JSONResult updateDoctor(@RequestBody DoctorDTO doctorDTO) {
        return doctorService.UpdateDoctor(doctorDTO);
    }


    @GetMapping("/level/getAll")
    @Operation(summary = "获取所有医生级别信息", description = "用于在下拉框中展示医生级别")
    public JSONResult getAllDoctorLevels() {
        return doctorService.getAllDoctorLevels();
    }

    @GetMapping("/type/getAll")
    @Operation(summary = "获取所有科室信息", description = "用于在下拉框中展示科室")
    public JSONResult getAllDepartments() {
        return doctorService.getAllDepartments();
    }


}
