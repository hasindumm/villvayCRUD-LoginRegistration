package com.example.demo1.service;


import com.example.demo1.model.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);
    List<User> getAllUsers();
}

