package com.guihang.medicalbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;
import com.guihang.medicalbackend.mapper.DoctorMapper;
import com.guihang.medicalbackend.pojo.Doctor;
import com.guihang.medicalbackend.pojo.DrugCompany;
import com.guihang.medicalbackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Override
    public JSONResult getAllDoctor(DoctorDTO doctorDTO) {

        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        List<Doctor> doctors = doctorMapper.selectList(wrapper);
        return new JSONResult(200, "查询成功", doctors);

    }
}
