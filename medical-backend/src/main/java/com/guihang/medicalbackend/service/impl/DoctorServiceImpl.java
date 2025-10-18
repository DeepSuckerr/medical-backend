package com.guihang.medicalbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.DoctorDTO;
import com.guihang.medicalbackend.mapper.AccountMapper;
import com.guihang.medicalbackend.mapper.DepartmentMapper;
import com.guihang.medicalbackend.mapper.DoctorLevelMapper;
import com.guihang.medicalbackend.mapper.DoctorMapper;
import com.guihang.medicalbackend.pojo.Account;
import com.guihang.medicalbackend.pojo.Department;
import com.guihang.medicalbackend.pojo.Doctor;
import com.guihang.medicalbackend.dto.AccountDTO;
import com.guihang.medicalbackend.pojo.DoctorLevel;
import com.guihang.medicalbackend.service.AccountService;
import com.guihang.medicalbackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorMapper doctorMapper;

    @Autowired
    AccountMapper accountMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    DoctorLevelMapper doctorLevelMapper;


    @Override
    public JSONResult getAllDoctor(String keyword, Integer currentPage, Integer pageSize) {

        QueryWrapper<Doctor> wrapper = new QueryWrapper<>();
        if (keyword != null && !keyword.trim().isEmpty()) {
            // 同时根据公司名、电话或ID进行模糊查询
            wrapper.and(i -> i.like("name", keyword)
                    .or().like("id", keyword)
                    .or().like("phone", keyword));
        }
        //是否分页查询判断
        if (currentPage != null && pageSize != null && currentPage > 0 && pageSize > 0) {
            Page<Doctor> page = new Page<>(currentPage, pageSize);
            //两个查询都判断完后都扔到selectPage中处理得到resultPage
            Page<Doctor> resultPage = doctorMapper.selectPage(page, wrapper);
            return new JSONResult(200, "查询成功", resultPage);
        } else {
            List<Doctor> companyList = doctorMapper.selectList(wrapper);
            return new JSONResult(200, "查询成功", companyList);
        }

    }

    @Override
    @Transactional
    public JSONResult DeleteDoctorById(Long id) {
        // 1. 根据id查询医生信息
        Doctor doctor = doctorMapper.selectById(id);

        // 2. 校验医生是否存在
        if (doctor == null) {
            return new JSONResult(201, "要删除的医生信息不存在", null);
        }

        // 3. 从医生信息中获取accountId
        Long accountId = doctor.getAccountId();

        // 4. 删除医生记录
        int doctorDeleteResult = doctorMapper.deleteById(id);

        // 5. 如果医生有关联账户，则删除账户记录
        if (accountId != null) {
            accountService.deleteAccountById(accountId);
        }

        if (doctorDeleteResult > 0) {
            return new JSONResult(200, "删除成功", null);
        } else {
            // 在事务下，如果代码能执行到这里，通常意味着发生了异常，事务会自动回滚
            return new JSONResult(202, "删除失败", null);
        }
    }

    @Override
    @Transactional
    public JSONResult UpdateDoctor(DoctorDTO doctorDTO) {
        // 1. 验证传入的ID是否有效
        if (doctorDTO == null || doctorDTO.getId() == null) {
            return new JSONResult(201, "更新请求中必须包含医生ID", null);
        }

        // 2. 从数据库中获取当前的医生信息
        Doctor existingDoctor = doctorMapper.selectById(doctorDTO.getId());

        // 3. 检查医生是否存在
        if (existingDoctor == null) {
            return new JSONResult(202, "找不到ID为 " + doctorDTO.getId() + " 的医生信息", null);
        }

        // 4. 处理手机号更新（包含唯一性校验）
        if (doctorDTO.getPhone() != null && !doctorDTO.getPhone().trim().isEmpty()) {
            // 检查手机号是否真的被修改了
            if (!doctorDTO.getPhone().equals(existingDoctor.getPhone())) {
                // 检查这个新手机号是否已被其他账户占用
                QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
                queryWrapper.eq("phone_number", doctorDTO.getPhone());
                Account accountWithNewPhone = accountMapper.selectOne(queryWrapper);

                if (accountWithNewPhone != null) {
                    return new JSONResult(203, "修改失败,该手机号已被其他用户注册", null);
                }

                // 更新医生表中的手机号
                existingDoctor.setPhone(doctorDTO.getPhone());

                // 同时更新账户表中的手机号
                Account associatedAccount = accountMapper.selectById(existingDoctor.getAccountId());
                if (associatedAccount != null) {
                    associatedAccount.setPhoneNumber(doctorDTO.getPhone());
                    accountMapper.updateById(associatedAccount);
                }
            }
        }

        // 5. 将DTO中的其他非空字段更新到现有医生对象中
        if (doctorDTO.getName() != null && !doctorDTO.getName().trim().isEmpty()) {
            existingDoctor.setName(doctorDTO.getName());
        }
        if (doctorDTO.getAge() != null) {
            existingDoctor.setAge(doctorDTO.getAge());
        }
        if (doctorDTO.getSex() != null) {
            existingDoctor.setSex(doctorDTO.getSex());
        }
        if (doctorDTO.getLevelId() != null) {
            existingDoctor.setLevelId(doctorDTO.getLevelId());
        }
        if (doctorDTO.getTypeId() != null) {
            existingDoctor.setTypeId(doctorDTO.getTypeId());
        }

        // 6. 更新修改时间
        existingDoctor.setUpdateTime(new Date());

        // 7. 将更新后的医生对象保存到数据库
        int result = doctorMapper.updateById(existingDoctor);

        // 8. 返回结果
        if (result > 0) {
            return new JSONResult(200, "医生信息更新成功", existingDoctor);
        } else {
            return new JSONResult(500, "医生信息更新失败", null);
        }
    }

    @Override
    public JSONResult addDoctor(DoctorDTO doctorDTO) {
        // 1. 基本信息校验
        if (doctorDTO == null) {
            return new JSONResult(201, "添加时信息不能为空", null);
        }
        if (doctorDTO.getName() == null || "".equals(doctorDTO.getName().trim())) {
            return new JSONResult(202, "医生姓名不能为空", null);
        }
        if (doctorDTO.getPhone() == null || "".equals(doctorDTO.getPhone().trim())) {
            return new JSONResult(203, "联系电话不能为空", null);
        }
//        if (doctorDTO.getPassword() == null || doctorDTO.getPassword().isEmpty()) {
//            return new JSONResult(208, "登录密码不能为空", null);
//        }
//        if (doctorDTO.getConfirmPassword() == null || !doctorDTO.getPassword().equals(doctorDTO.getConfirmPassword())) {
//            return new JSONResult(209, "两次输入的密码不一致", null);
//        }

        // 2. 准备用于注册的AccountDTO
        AccountDTO accountToRegister = new AccountDTO();
        accountToRegister.setRealName(doctorDTO.getName());
        accountToRegister.setPhoneNumber(doctorDTO.getPhone());
        accountToRegister.setPwd(doctorDTO.getPassword());
        accountToRegister.setUname(generateUniqueUsername()); // 自动生成用户名
        accountToRegister.setUtype("ROLE_2"); // 默认ROLE_2是医生角色

        // 3. 调用AccountService的注册方法
        JSONResult registerResult = accountService.register(accountToRegister);

        // 4. 处理注册结果
        if (registerResult.getCode() != 200) {
            // 如果注册失败（例如用户名已存在），则直接返回失败信息
            return registerResult;
        }

        // 5. 注册成功，获取新创建的Account对象
        Doctor doctor = getDoctor(doctorDTO, registerResult);

        int i = doctorMapper.insert(doctor);

        // 7. 返回最终结果
        if (i > 0) {
            return new JSONResult(200, "新增医生成功", null);
        } else {
            // 注意：这里可能需要添加事务回滚逻辑，以防医生信息保存失败但账户已创建
            return new JSONResult(500, "新增医生失败，但账户可能已创建，请检查数据", null);
        }
    }

    @Override
    public JSONResult getAllDoctorLevels() {
        List<DoctorLevel> doctorLevels = doctorLevelMapper.selectList(null);
        return new JSONResult(200, "查询成功", doctorLevels);
    }

    @Override
    public JSONResult getAllDepartments() {
        List<Department> departments = departmentMapper.selectList(null);
        return new JSONResult(200, "查询成功", departments);
    }

    private static Doctor getDoctor(DoctorDTO doctorDTO, JSONResult registerResult) {
        Account newAccount = (Account) registerResult.getData();

        // 6. 创建并保存Doctor信息
        Doctor doctor = new Doctor();
        doctor.setName(doctorDTO.getName());
        doctor.setPhone(doctorDTO.getPhone());
        doctor.setAge(doctorDTO.getAge());
        doctor.setSex(doctorDTO.getSex());
        doctor.setLevelId(doctorDTO.getLevelId());
        doctor.setTypeId(doctorDTO.getTypeId());
        doctor.setCreateTime(new Date());
        doctor.setUpdateTime(new Date());
        doctor.setAccountId(newAccount.getId()); // 关联新创建的账户ID
        return doctor;
    }

    private String generateUniqueUsername() {
        // 生成一个稍微可读的、带随机数字的用户名
        Random random = new Random();
        int number = 1000 + random.nextInt(9000); // 生成一个1000-9999的随机数
        return "user" + number;
    }
}
