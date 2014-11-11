package com.duoshenyi.backend.user.ex;

import com.duoshenyi.backend.user.model.User;

/**
 * Created by wuyaohui on 14-11-11.
 */
public class UserExistsException extends UserException{

    public UserExistsException() {
        super();
    }

    public UserExistsException(String message) {
        super(message);
    }

    public UserExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserExistsException(Throwable cause) {
        super(cause);
    }

    protected UserExistsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public UserExistsException(User user) {
        this("user "+user+" already exists.");
    }
}
