package com.jmp.service;

import com.jmp.dao.UserDao;
import com.jmp.model.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Ales on 24.05.2017.
 */
@Service("userDetailsService")
public class UniversityUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        com.jmp.model.User user = userDao.findUserByName(username);
        List<GrantedAuthority> authorities =
                buildUserAuthority(user.getRole());

        return buildUserForAuthentication(user, authorities);
    }

    private User buildUserForAuthentication(com.jmp.model.User user,
                                            List<GrantedAuthority> authorities) {
        return new User(user.getName(), user.getPassword(), authorities);
    }

    private List<GrantedAuthority> buildUserAuthority(Role role) {
        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();
        setAuths.add(new SimpleGrantedAuthority(role.getUserRole()));
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>(setAuths);
        return result;
    }
}
