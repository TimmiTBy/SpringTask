package com.jmp.dao;



import com.jmp.model.UserDocument;

import java.util.List;

/**
 * Created by Ales on 28.05.2017.
 */
public interface UserDocumentDao {

    void save(UserDocument document);

    List<UserDocument> findAllByUserId(int userId);

    UserDocument findById(int id);
}
