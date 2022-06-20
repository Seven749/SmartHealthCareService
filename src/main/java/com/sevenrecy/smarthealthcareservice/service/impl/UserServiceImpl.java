package com.sevenrecy.smarthealthcareservice.service.impl;

import com.sevenrecy.smarthealthcareservice.dao.UserDao;
import com.sevenrecy.smarthealthcareservice.entity.User;
import com.sevenrecy.smarthealthcareservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public User selectSysUserById(int user_id) {
        return userDao.selectSysUserById(user_id);
    }

    @Override
    public User selectSysUserByTel(String tel) {
        return userDao.selectSysUserByTel(tel);
    }

    @Override
    public User selectSysUserByIDCard(String IDCard) {
        return userDao.selectSysUserByIDCard(IDCard);
    }

    @Override
    public int insertSysUser(User user) {
        return userDao.insertSysUser(user);
    }


    @Override
    public User selectSysUserByIDCardAndTel(String IDCard, String tel) {
        return userDao.selectSysUserByIDCardAndTel(IDCard, tel);
    }

    @Override
    public int updateSysUserPwd(int user_id, String tel, String pwd) {
        return userDao.updateSysUserPwd(user_id, tel, pwd);
    }

    @Override
    public int updateBalance(int user_id, double balance) {
        return userDao.updateBalance(user_id, balance);
    }
}
