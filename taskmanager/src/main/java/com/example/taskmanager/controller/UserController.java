package com.example.taskmanager.controller;

import com.example.taskmanager.apiResponse.WeatherResponse;
import com.example.taskmanager.entity.JournalEntry;
import com.example.taskmanager.entity.User;
import com.example.taskmanager.repository.UserRepository;
import com.example.taskmanager.service.JournalEntryService;
import com.example.taskmanager.service.UserService;
import com.example.taskmanager.service.WeatherService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")  //end -point for api hitting
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String userName=authentication.getName();
            User userInDb=userService.findByUserName(userName);
            userInDb.setUserName(user.getUserName());
            userInDb.setPassword(user.getPassword());
            userService.saveNewUser(userInDb);
        } catch (RuntimeException e) {
            throw new RuntimeException("problem with authentication"+e);
        }
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @DeleteMapping
    public ResponseEntity<?> deleteUserById(@RequestBody User user){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse= weatherService.getWeather("Delhi");
        String greetings="";
        if(weatherResponse!=null){
            greetings="weather feels like"+weatherResponse.getCurrent().getFeelsLikeC();
        }
        return new ResponseEntity<>("Hi"+ authentication.getName()+greetings,HttpStatus.NO_CONTENT);
    }

}
