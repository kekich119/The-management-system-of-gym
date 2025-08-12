package com.kekich.gymsystem.repository;

import com.kekich.gymsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {


    User findByName(String name);

    Optional<User> findByLastName(String lastName);

    @Transactional
    void deleteByEmail(String email);

    User findByEmail(String email);
}
