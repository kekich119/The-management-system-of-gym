package com.kekich.gymsystem.service;

import org.springframework.stereotype.Service;

@Service
public class Validator {

    public boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty() && name.length() <= 50;
    }

    public boolean isValidLastName(String lastName) {
        return lastName != null && !lastName.trim().isEmpty() && lastName.length() <= 50;
    }


    public boolean isValidEmail(String email) {
        return email != null && !email.trim().isEmpty() && email.length() <= 100 && email.contains("@");
    }

    public boolean isValidSpecialCode(int specialCode) {
        return specialCode >= 100000 && specialCode <= 999999;
    }



    public boolean isValidAll(String name, String lastName, String email, int specialCode) {
        return isValidName(name) && isValidLastName(lastName) && isValidEmail(email) && isValidSpecialCode(specialCode);
    }


}
