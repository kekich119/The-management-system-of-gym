package com.kekich.gymsystem.controller;


import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add/user")
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
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

}
