package com.example.taskmanager.controller;

import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";

    }
    @PostMapping("/create-user")
    public void createEntry(@RequestBody User user){
        userService.saveNewUser(user);
    }
}
