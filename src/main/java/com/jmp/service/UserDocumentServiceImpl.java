package com.jmp.service;

import com.jmp.dao.UserDocumentDao;
import com.jmp.model.UserDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Ales on 28.05.2017.
 */
@Service("userDocumentService")
@Transactional
public class UserDocumentServiceImpl implements UserDocumentService {

    @Autowired
    UserDocumentDao userDocumentDao;

    @Override
    public List<UserDocument> findAllByUserId(int id) {
        return userDocumentDao.findAllByUserId(id);
    }

    @Override
    public void saveDocument(UserDocument document) {
        userDocumentDao.save(document);
    }

    @Override
    public UserDocument findById(int id) {
        return userDocumentDao.findById(id);
    }
}
