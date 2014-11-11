package com.duoshenyi.backend.user.ex;

import com.duoshenyi.backend.user.model.User;

/**
 * Top exception of user module.
 */
public class UserException extends RuntimeException{

    private User user;

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserException(Throwable cause) {
        super(cause);
    }

    protected UserException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    protected UserException(User user){
        this(user.toString());
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
