package com.guihang.medicalbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.AccountDTO;
import com.guihang.medicalbackend.mapper.AccountMapper;
import com.guihang.medicalbackend.pojo.Account;
import com.guihang.medicalbackend.service.AccountService;
import com.guihang.medicalbackend.utils.JwtToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountMapper accountMapper;

    public JSONResult login(AccountDTO accountDTO) {
        if (accountDTO.getUname() == null || accountDTO.getPwd() == null) {
            return new JSONResult(201, "登录信息必填", null);
        }
        if ("".equals(accountDTO.getUname()) || accountDTO.getUname() == null) {
            return new JSONResult(202, "请输入用户名", null);
        }
        if ("".equals(accountDTO.getPwd()) || accountDTO.getPwd() == null) {
            return new JSONResult(203, "请输入密码", null);
        }

        // 根据用户名查询账户
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", accountDTO.getUname());
        Account account = accountMapper.selectOne(queryWrapper);

        // 验证用户是否存在
        if (account == null) {
            return new JSONResult(204, "请检查用户名或密码是否正确", null);
        }
//        // 使用MD5验证密码
//        if (!DM5Password.verify(accountDTO.getPwd(), account.getPwd())) {
//            return new JSONResult(204,"请检查用户名或密码是否正确",null);
//        }
        //下放令牌
        String token = JwtToken.createToken(account.getId(), account.getUname());
        System.out.println("token:" + token);
        AccountDTO accountDTO1 = new AccountDTO();
        BeanUtils.copyProperties(account, accountDTO1);
        accountDTO1.setToken(token);

        return new JSONResult(200, "登录成功", accountDTO1);
    }


}