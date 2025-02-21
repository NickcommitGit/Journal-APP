package com.example.taskmanager.controller;

import com.example.taskmanager.apiResponse.WeatherResponse;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";

    }
    @PostMapping("/create-user")
    public void createEntry(@RequestBody User user){
        userService.saveNewUser(user);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
      //  Authentication authentication= SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse= weatherService.getWeather("Delhi,India");
        String greetings="";
        if(weatherResponse!=null){
            greetings="weather feels like : "+weatherResponse.getCurrent().getFeelsLikeC();
        }
        return new ResponseEntity<>("Hi "+ greetings, HttpStatus.NO_CONTENT);
    }
}
