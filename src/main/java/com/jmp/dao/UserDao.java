package com.jmp.dao;

import com.jmp.model.User;

/**
 * Created by Ales on 22.05.2017.
 */
public interface UserDao {

    User findUserByName(String name);
}
