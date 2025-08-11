package com.kekich.gymsystem.service;

import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.repository.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UsersRepository usersRepository;

    public UserService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }


    public User addUser(User user) {
        return usersRepository.save(user);
    }
}
