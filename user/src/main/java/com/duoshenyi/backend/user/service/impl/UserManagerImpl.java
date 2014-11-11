package com.duoshenyi.backend.user.service.impl;

import com.duoshenyi.backend.user.dao.UserDao;
import com.duoshenyi.backend.user.model.User;
import com.duoshenyi.backend.user.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;

/**
 * Created by wuyaohui on 14-11-11.
 */
@Service
@Transactional
public class UserManagerImpl implements UserManager{

    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public User getUserByAccount(String account) {
        return userDao.getUser(account);
    }

    @Override
    public User addUser(User user) {
        Serializable userId = userDao.saveUser(user);
        return userDao.getUserById(userId.toString());
    }
}
