package com.guihang.medicalbackend.service;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DrugCompanyDTO;

public interface DrugCompanyService {

    /**
     * 查询公司信息
     * @param keyword     模糊查询关键字
     * @param currentPage 当前页
     * @param pageSize    每页显示的条数
     */
    JSONResult getCompanyAll(String keyword, Integer currentPage, Integer pageSize);

    JSONResult addCompany(DrugCompanyDTO drugCompanyDTO);

    JSONResult deleteCompany(Long companyId);

    JSONResult updateCompany(DrugCompanyDTO drugCompanyDTO);
}
