package com.jmp.service;

import com.jmp.model.UserDocument;

import java.util.List;

/**
 * Created by Ales on 28.05.2017.
 */
public interface UserDocumentService {

    List<UserDocument> findAllByUserId(int id);

    void saveDocument(UserDocument document);

    UserDocument findById(int id);

}
