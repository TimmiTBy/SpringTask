package com.jmp.dao;

import com.jmp.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ales on 22.05.2017.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl() {
    }

    public UserDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public User findUserByName(String name) {
        List<User> userList = new ArrayList<User>();
        Query query = sessionFactory.getCurrentSession().createQuery("from User u where u.name = :name");
        query.setParameter("name", name);
        userList = query.list();
        if (userList.size() > 0)
            return userList.get(0);
        else
            return null;
    }
}
