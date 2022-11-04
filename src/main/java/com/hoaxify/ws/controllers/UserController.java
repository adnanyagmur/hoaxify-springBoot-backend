package com.hoaxify.ws.controllers;

import com.hoaxify.ws.entities.User;
import com.hoaxify.ws.errors.ApiError;
import com.hoaxify.ws.responses.GenericResponse;
import com.hoaxify.ws.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/api/1.0/users")
    public GenericResponse createUser(@RequestBody @Valid  User user){

        userService.save(user);
        return new GenericResponse("User created");
    }
}
