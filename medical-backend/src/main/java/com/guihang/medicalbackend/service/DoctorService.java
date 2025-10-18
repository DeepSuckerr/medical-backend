package com.guihang.medicalbackend.service;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;
import com.guihang.medicalbackend.pojo.Department;
import com.guihang.medicalbackend.pojo.Doctor;
import com.guihang.medicalbackend.pojo.DoctorLevel;

public interface DoctorService {
    JSONResult getAllDoctor(String keyword, Integer currentPage, Integer pageSize);

    JSONResult DeleteDoctorById(Long id);

    JSONResult UpdateDoctor(DoctorDTO doctorDTO);

    JSONResult addDoctor(DoctorDTO doctorDTO);

    JSONResult getAllDoctorLevels();

    JSONResult getAllDepartments();
}
