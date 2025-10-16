package com.guihang.medicalbackend.service;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;

public interface DoctorService {
    JSONResult getAllDoctor(DoctorDTO doctorDTO);
}
