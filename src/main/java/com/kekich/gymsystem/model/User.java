package com.kekich.gymsystem.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "users")
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "date_birth")
    @JsonProperty("date_birth")
    private LocalDate birthday;
    @Column(name = "special_code")
    private int special_code;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "date_subscription_start")
    private LocalDate date_subscription_start;
    @Column(name = "date_subscription_finish")
    private LocalDate date_subscription_finish;
    @Column(name = "pay_for")
    private int pay_for;
    @Column(name = "active")
    private boolean active;


}
