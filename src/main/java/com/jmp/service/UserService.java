package com.jmp.service;

import com.jmp.model.User;

/**
 * Created by Ales on 23.05.2017.
 */
public interface UserService {

    User findUserByName(String name);
}
