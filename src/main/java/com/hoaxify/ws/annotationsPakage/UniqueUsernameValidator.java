package com.hoaxify.ws.annotationsPakage;

import com.hoaxify.ws.entities.User;
import com.hoaxify.ws.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername,String> {

    @Autowired
    UserRepository userRepository;


    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        User user = userRepository.findByUsername(username);
        if(user!=null){
            return false;
        }
        return true;
    }
}
