package com.guihang.medicalbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DrugCompanyDTO;
import com.guihang.medicalbackend.mapper.DrugCompanyMapper;
import com.guihang.medicalbackend.pojo.DrugCompany;
import com.guihang.medicalbackend.service.DrugCompanyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DrugCompanyServiceImpl implements DrugCompanyService {

    @Autowired
    private DrugCompanyMapper drugCompanyMapper;

    @Override
    public JSONResult getCompanyAll(String keyword, Integer currentPage, Integer pageSize) {
        Page<DrugCompany> pa = new Page<>(currentPage, pageSize);
        //是否模糊查询判断
        if (keyword == null || keyword.isEmpty()) {
            Page<DrugCompany> page = drugCompanyMapper.selectPage(pa, null);
//            System.out.println("分页之后的数据" + page1.getRecords());
//            System.out.println("数据的总记录数" + page1.getTotal());
//            System.out.println("当前页码" + page1.getCurrent());
//            System.out.println("每页的条数" + page1.getSize());
//            System.out.println("总页数" + page1.getPages());
            if (page.getRecords().isEmpty()) {
                return new JSONResult(201, "暂无数据");
            }
            return new JSONResult(200, "查询成功", page);
        }
        if (currentPage > 1) {
            currentPage = 1;
        }
        // 同时根据公司名、电话或ID进行模糊查询
        QueryWrapper<DrugCompany> wrapper = new QueryWrapper<>();
        wrapper.and(i -> i.like("company_name", keyword)
                .or().like("company_phone", keyword)
                .or().like("company_id", keyword));
        Page<DrugCompany> page = drugCompanyMapper.selectPage(pa, wrapper);
        if (page.getRecords().isEmpty()) {
            return new JSONResult(201, "药物公司信息不存在");
        }
        return new JSONResult(200, "查询成功", page);
    }

    @Override
    public JSONResult addCompany(DrugCompanyDTO drugCompanyDTO) {
        if (drugCompanyDTO == null) {
            return new JSONResult(201, "添加时信息不能为空");
        }
        if ("".equals(drugCompanyDTO.getCompanyName())) {
            return new JSONResult(202, "公司名称不能为空");
        }
        if ("".equals(drugCompanyDTO.getCompanyPhone())) {
            return new JSONResult(202, "公司电话不能为空");
        }
        //
        DrugCompany drugCompany = new DrugCompany();
        BeanUtils.copyProperties(drugCompanyDTO, drugCompany);
        drugCompany.setCreateTime(new Date());
        drugCompany.setUpdateTime(new Date());
        int row = drugCompanyMapper.insert(drugCompany);
        if (row != 1) {
            return new JSONResult(203, "添加数据失败，请稍后再试！");
        }
        return new JSONResult(200, "添加成功");
    }

    @Override
    public JSONResult deleteCompany(Long companyId) {
        if (companyId == null || companyId <= 0) {
            return new JSONResult(400, "无效的公司ID");
        }
        try {
            int i = drugCompanyMapper.deleteById(companyId);
            if (i != 1) {
                return new JSONResult(404, "删除失败，未找到对应的公司或数据已被删除");
            } else {
                return new JSONResult(200, "删除成功");
            }
        } catch (Exception e) {
            // 在实际项目中，这里应该记录日志
            // log.error("删除公司时发生异常", e);
            return new JSONResult(500, "删除失败，服务器内部错误");
        }
    }

    @Override
    public JSONResult updateCompany(DrugCompanyDTO drugCompanyDTO) {
        if (drugCompanyDTO == null || drugCompanyDTO.getCompanyId() == null || drugCompanyDTO.getCompanyId() <= 0) {
            return new JSONResult(201, "无效的公司ID");
        }
        DrugCompany drugCompany = new DrugCompany();
        BeanUtils.copyProperties(drugCompanyDTO, drugCompany);
        drugCompany.setUpdateTime(new Date());
        int i = drugCompanyMapper.updateById(drugCompany);
        if (i == 1) {
            return new JSONResult(200, "更新成功");
        }
        return new JSONResult(202, "更新失败");
    }
}
