package com.kekich.gymsystem.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kekich.gymsystem.model.User;
import com.kekich.gymsystem.repository.UsersRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {


    JedisPool jedisPool = new JedisPool("localhost", 6379);
    private final UsersRepository usersRepository;
    private ObjectMapper mapper;

    public UserService(UsersRepository usersRepository, ObjectMapper mapper) {
        this.usersRepository = usersRepository;
        this.mapper = mapper;
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
    public User getCachedUsers(String name) {
        try (Jedis jedis = jedisPool.getResource()) {
            String key = "user:%s".formatted(name);
            String raw = jedis.get(key);
            if (raw != null) {
                return mapper.readValue(raw, User.class);
            }
            var user = getUserByName(name);
            if (user == null){
                return null;
            }
            jedis.setex(key, 10, mapper.writeValueAsString(user));
            return user;

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }



}
