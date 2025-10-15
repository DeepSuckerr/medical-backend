package com.guihang.medicalbackend.service;


import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.AccountDTO;

public interface AccountService {
    JSONResult login(AccountDTO accountDTO);
}
