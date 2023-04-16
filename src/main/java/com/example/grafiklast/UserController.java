package com.example.grafiklast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/user/details")
    public User getUser(@RequestParam String name ) throws InterruptedException, ExecutionException{
        return userService.getUserDetails(name);
    }

    @PostMapping(value = "/user/create", consumes = "multipart/form-data")
    public String createUser(@ModelAttribute User user) throws InterruptedException, ExecutionException {
        return userService.saveUserDetails(user);
    }

    @PutMapping("/user/update")
    public String updateUser(@RequestBody User user  ) throws InterruptedException, ExecutionException {
        return userService.updateUserDetails(user);
    }

    @DeleteMapping("/user/delete")
    public String deleteUser(@RequestParam String name){
        return userService.deleteUser(name);
    }
}