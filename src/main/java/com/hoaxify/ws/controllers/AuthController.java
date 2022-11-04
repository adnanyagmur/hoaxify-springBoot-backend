package com.hoaxify.ws.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.hoaxify.ws.entities.User;
import com.hoaxify.ws.entities.Views;
import com.hoaxify.ws.repos.UserRepository;
import com.hoaxify.ws.responses.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private final UserRepository userRepository;

   // PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @PostMapping("/api/1.0/auth")
    @JsonView(Views.Base.class)
    ResponseEntity<?> handleAuthentication(@CurrentUser User user){

        return ResponseEntity.ok().body(user);
    }

}
