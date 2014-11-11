package com.duoshenyi.backend.user.dao.impl;

import com.duoshenyi.backend.user.dao.UserDao;
import com.duoshenyi.backend.user.ex.UserExistsException;
import com.duoshenyi.backend.user.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class UserDaoImpl implements UserDao {

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public User getUser(String account) {

        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("account",account));

        return (User)criteria.uniqueResult();
    }

    @Override
    public User getUserById(String userId) {
        Session session = sessionFactory.getCurrentSession();
        return (User)session.get(User.class,userId);
    }

    @Override
    public Serializable saveUser(User user) {
        User u = getUser(user.getAccount());
        if(u!=null){
            throw new UserExistsException(u);
        }
        Session session = sessionFactory.getCurrentSession();
        return session.save(user);
    }
}
