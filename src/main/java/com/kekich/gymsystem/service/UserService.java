package com.kekich.gymsystem.service;

import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.repository.UsersRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public User addUser(User user) {
        return usersRepository.save(user);
    }

    public List<User> getAllUsers() {
        return usersRepository.findAll();
    }

    public User getUserByName(String userName) {
        return usersRepository.findByName(userName);
    }


    public Optional<User> getUserByLastName(String lastName) {
        return usersRepository.findByLastName(lastName);
    }

    @Transactional
    public void deleteUserByEmail(String email) {
        usersRepository.deleteByEmail(email.trim().toLowerCase());
    }


    @Transactional
    public void updateUserByEmail(String email,String newName) {
        User user = usersRepository.findByEmail(email.trim().toLowerCase());
        user.setName(newName);
    }

}
