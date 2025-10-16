package com.guihang.medicalbackend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guihang.medicalbackend.commons.JSONResult;
import com.guihang.medicalbackend.dto.AccountDTO;
import com.guihang.medicalbackend.mapper.AccountMapper;
import com.guihang.medicalbackend.pojo.Account;
import com.guihang.medicalbackend.service.AccountService;
import com.guihang.medicalbackend.utils.DM5Password;
import com.guihang.medicalbackend.utils.JwtToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

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

        String newMd5 = DM5Password.md5PassWord(accountDTO.getPwd(), account.getSalt());

        if (account.getPwd().equals(newMd5)){
            return new JSONResult(200,"登录成功",null);
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

    @Override
    public JSONResult register(AccountDTO accountDTO) {
        // 参数校验
        if (accountDTO.getUname() == null || accountDTO.getPwd() == null) {
            return new JSONResult(201,"注册信息必填",null);
        }
        if ("".equals(accountDTO.getUname()) || accountDTO.getUname()==null) {
            return new JSONResult(202,"请输入用户名",null);
        }
        if ("".equals(accountDTO.getPwd()) || accountDTO.getPwd()==null) {
            return new JSONResult(203,"请输入密码",null);
        }
        if ("".equals(accountDTO.getRealName()) || accountDTO.getRealName()==null) {
            return new JSONResult(204,"请输入用户真实姓名",null);
        }
        if ("".equals(accountDTO.getPhoneNumber()) || accountDTO.getPhoneNumber()==null) {
            return new JSONResult(204,"用户注册手机号码不能为空",null);
        }

        // 检查uType是否有效
        String uType = accountDTO.getUtype();
        if (uType == null || (!"ROLE_1".equals(uType) && !"ROLE_2".equals(uType) && !"ROLE_3".equals(uType))) {
            return new JSONResult(206, "无效的用户类型", null);
        }

        // 检查用户名是否已存在
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname", accountDTO.getUname());
        Account existingAccount = accountMapper.selectOne(queryWrapper);
        if (existingAccount != null) {
            return new JSONResult(205,"用户名已存在",null);
        }

        // 对这个用户对应的密码进行加密
        String salt = UUID.randomUUID().toString();// 生成一个36位的唯一的字符串
        System.out.println("salt="+salt);
        //密码
        String newMd5 = DM5Password.md5PassWord(accountDTO.getPwd(), salt);
        Account account = new Account();
        BeanUtils.copyProperties(accountDTO, account);
        account.setSalt(salt);
        account.setPwd(newMd5);
        account.setCreateTime(new Date());
        account.setUpdateTime(new Date());

        // 将加密之后的密码newMd5   和 salt 存储到数据库中
        // 根据用户名查找当前用户信息

        // 保存到数据库
        int result = accountMapper.insert(account);
        if (result > 0) {
            return new JSONResult(200,"注册成功",account);
        } else {
            return new JSONResult(500,"注册失败",null);
        }
    }
}