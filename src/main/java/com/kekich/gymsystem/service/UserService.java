package com.kekich.gymsystem.service;

import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.repository.UsersRepository;
import org.springframework.scheduling.annotation.Scheduled;
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
    public void updateUserByEmail(String email, String newName) {
        User user = usersRepository.findByEmail(email.trim().toLowerCase());
        user.setName(newName);
    }

    public User getUserByEmail(String email) {
        return usersRepository.findByEmail(email.trim().toLowerCase());
    }

    @Scheduled(cron = "@daily")
    public void checkSubscription() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            if (user.isActive() && user.getDate_subscription_finish().isBefore(java.time.LocalDate.now())) {
                user.setActive(false);
                usersRepository.save(user);
            }else {
                user.setActive(true);
                usersRepository.save(user);
            }
        }
    }


    public User getUserBySpecialCode(int specialCode){
        return usersRepository.findBySpecialCode(specialCode);
    }

    public void checkSubscriptionNow() {
        List<User> users = usersRepository.findAll();
        for (User user : users) {
            if (user.isActive() && user.getDate_subscription_finish().isBefore(java.time.LocalDate.now())) {
                user.setActive(false);
                usersRepository.save(user);
            }else {
                user.setActive(true);
                usersRepository.save(user);
            }
        }
    }

}
