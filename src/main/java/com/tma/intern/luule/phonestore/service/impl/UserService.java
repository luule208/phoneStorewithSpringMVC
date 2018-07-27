package com.tma.intern.luule.phonestore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import com.tma.intern.luule.phonestore.model.User;
import com.tma.intern.luule.phonestore.repository.UserRepository;

@Service
@Component
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(int id) {

        return userRepository.findAllById(id);
    }

    public User add(User user) {
        return userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public User loadUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public boolean checkLogin(User user) {
        User u = userRepository.findByUsername(user.getUsername());
        if (u == null) {
            return false;
        }
        if (u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())) {
            return true;
        }
        return false;

    }
}
