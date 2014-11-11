package com.duoshenyi.backend.user.service;

import com.duoshenyi.backend.user.model.User;

import java.io.Serializable;

/**
 * Created by wuyaohui on 14-11-11.
 */
public interface UserManager {

    /**
     * Get user by his account.
     * @param account
     * @return user Object.
     */
    public User getUserByAccount(String account);

    /**
     * Add a new user.
     * @param user
     * @return null if fail to.
     */
    public User addUser(User user);
}
