package com.kekich.gymsystem.repository;

import com.kekich.gymsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface dataBaseRepository extends JpaRepository<User, Long> {

}
