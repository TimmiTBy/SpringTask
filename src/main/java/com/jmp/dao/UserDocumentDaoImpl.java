package com.jmp.dao;


import com.jmp.model.UserDocument;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ales on 28.05.2017.
 */
@Repository("userDocumentDao")
public class UserDocumentDaoImpl implements UserDocumentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDocumentDaoImpl() {
    }

    @Override
    public void save(UserDocument document) {
        sessionFactory.getCurrentSession().persist(document);
    }


    public UserDocument findById(int id) {
        return sessionFactory.getCurrentSession().get(UserDocument.class, id);
    }

    @Override
    public List<UserDocument> findAllByUserId(int userId) {
        List<UserDocument> userDocuments = new ArrayList<UserDocument>();
        Query query = sessionFactory.getCurrentSession().createQuery("from UserDocument d where d.user.id = :userId");
        query.setParameter("userId", userId);
        userDocuments = query.list();
        return userDocuments;
    }
}
