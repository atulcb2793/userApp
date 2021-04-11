package com.userapp.controller;

import com.userapp.model.User;
import com.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    
    @RequestMapping("/")
    public String welcome(){
        return "Welcome to User App !";
    }

    @RequestMapping("/api/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @RequestMapping("/api/user/friends/{id}")
    public List<User> getUserFriends(@PathVariable String id){
        return userService.getUserFriends(id);
    }

    @RequestMapping("/api/user/suggestions/{id}")
    public List<User> getUserFriendSuggestions(@PathVariable String id){
    	return userService.getUserFriendSuggestions(id);
    }

}
