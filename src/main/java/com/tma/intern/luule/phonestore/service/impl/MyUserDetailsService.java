package com.tma.intern.luule.phonestore.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.tma.intern.luule.phonestore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.tma.intern.luule.phonestore.model.User;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userService.loadUserByUsername(username) == null) throw new UsernameNotFoundException(" not found");
        User u = userService.loadUserByUsername(username);
        String password = u.getPassword();
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(u.getRoles());
        authorities.add(authority);
        MyUserDetails userDetail = new MyUserDetails(username, password, authorities);
        return userDetail;



    }
}