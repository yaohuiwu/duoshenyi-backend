package com.duoshenyi.backend.user.dao;

import com.duoshenyi.backend.user.model.User;

import java.io.Serializable;

/**
 * Created by wuyaohui on 14-11-11.
 */
public interface UserDao {

    /**
     * Get user by account.
     * @param account
     * @return
     */
    public User getUser(String account);

    /**
     * Get User by id.
     * @param userId
     * @return
     */
    public User getUserById(String userId);

    /**
     * Save a new user.
     * @param user
     * @return
     */
    public Serializable saveUser(User user);
}
