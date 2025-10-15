package com.guihang.medicalbackend.controller;

import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DrugCompanyDTO;
import com.guihang.medicalbackend.service.DrugCompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/drug")
@CrossOrigin
@Tag(name = "医药公司模块", description = "这个是医药公司的操作接口")
public class DrugCompanyController {

    @Autowired
    private DrugCompanyService drugCompanyService;

    @GetMapping("/getCompanyAll")
    @Operation(summary = "获取所有医药公司信息", description = "获取所有医药公司信息的接口")
    public JSONResult getCompanyAll(@RequestParam(name = "keyword", required = false) String keyword,
                                    @RequestParam(name = "currentPage", defaultValue = "1", required = false) Integer currentPage,
                                    @RequestParam(name = "pageSize", defaultValue = "10", required = false) Integer pageSize) {
        return drugCompanyService.getCompanyAll(keyword, currentPage, pageSize);
    }

    @PostMapping("/addCompany")
    @CrossOrigin
    @Operation(summary = "添加医药公司信息", description = "添加医药公司信息接口")
    public JSONResult addCompany(@RequestBody DrugCompanyDTO drugCompanyDTO) {
        return drugCompanyService.addCompany(drugCompanyDTO);
    }

    @DeleteMapping("/deleteCompany/{companyId}")
    @CrossOrigin
    @Operation(summary = "删除医药公司信息", description = "根据id删除医药公司信息")
    public JSONResult deleteCompany(@PathVariable("companyId") Long companyId) {
        return drugCompanyService.deleteCompany(companyId);
    }

    @PostMapping("/updateCompany")
    @CrossOrigin
    @Operation(summary = "更新医药公司信息", description = "根据id更新医药公司信息")
    public JSONResult updateCompany(@RequestBody DrugCompanyDTO drugCompanyDTO) {
        return drugCompanyService.updateCompany(drugCompanyDTO);
    }


}
