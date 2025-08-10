package com.kekich.gymsystem.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    private long id;
    private String name;
    private String last_name;
    @Column(name = "date_birth")
    private Date birthday;
    private int special_code;
    private String email;
    private String password;
    private Date date_subscription_start;
    private Date date_subscription_finish;
    private int pay_for;
    private boolean active;



}
