package com.example.demo1.controller;

import com.example.demo1.model.User;
import com.example.demo1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Slf4j
public class UserController {
    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;

    }

    @GetMapping("")
    public String viewHomePage() {
        log.info("view Home page ");
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {

        model.addAttribute("user", new User());
        log.info("View Signup form");
        return "signup_form";
    }

    @PostMapping("/process_register")
    public String processRegister(User user) {

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);

        userService.saveUser(user);
        log.info("Register the user");
        return "register_success";
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
        List<User> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        log.info("Retrive system users");
        return "users";
    }


}
