package com.kekich.gymsystem.controller;


import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.service.UserService;
import com.kekich.gymsystem.service.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserRestController {

    private Validator validator;
    private final UserService userService;

    public UserRestController(UserService userService, Validator validator) {
        this.userService = userService;
        this.validator = validator;

    }

    @PostMapping("/add/user")
    public User addUser(@RequestBody User user) {
       if (validator.isValidAll(user.getName(),user.getLastName(),user.getEmail(),user.getSpecial_code())) {
           return userService.addUser(user);
       }
       else {
              throw new IllegalArgumentException("Invalid user data: Name and LastName must be non-empty and up to 50 characters long.");
       }


    }

    @GetMapping("/get/users")
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/get/user/by-name")
    public User getUser(@RequestParam("name") String name) {
        return userService.getUserByName(name);
    }

    @GetMapping("/get/user/by-lastName")
    public ResponseEntity<User> getUserByLastName(@RequestParam("lastName") String lastName) {
        return userService.getUserByLastName(lastName).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/user/by-email/")
    public void deleteUserByEmail(@RequestParam("email") String email) {
        userService.deleteUserByEmail(email);
    }

    @PutMapping("/update/user/")
    public void ubpadeUserByEmail(@RequestParam("email") String email, @RequestParam("new-name") String newName) {
         userService.updateUserByEmail(email, newName);
    }


    @PutMapping("/extension/by-email/")
    public void extensionByEmail(@RequestParam("email") String email) {
        User user = userService.getUserByEmail(email);
        if (user != null) {
            user.setDate_subscription_finish(user.getDate_subscription_finish().plusDays(30));
            user.setDate_subscription_start(user.getDate_subscription_start().plusDays(30));
            userService.addUser(user);
        }
    }

}
