package com.nailton.apiCooking.controllers;

import com.nailton.apiCooking.configuration.Util.Middlewares;
import com.nailton.apiCooking.dto.AuthenticationDTO;
import com.nailton.apiCooking.dto.UserDTO;
import com.nailton.apiCooking.models.User;
import com.nailton.apiCooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> insertUser(@RequestBody UserDTO userDTO) {
        User validUser = this.userService.findUserByEmail(userDTO.email());
        if (validUser != null) {
            String exist = "User exist";
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exist);
        } else {
            boolean isTrue = Middlewares.validCredentials(userDTO.email(), userDTO.password());
            if (isTrue) {
                try {
                    String created = "Created User";
                    this.userService.insertUser(userDTO.toEntity());
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(created);
                } catch (Exception e){
                    return ResponseEntity.internalServerError().build();
                }
            } else {
                String invalidFields = "Invalid Fields";
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(invalidFields);
            }
        }
    }

    @PostMapping(value = "/login")
    public  ResponseEntity<String> login(@RequestBody AuthenticationDTO authenticationDTO) {
        try {
            String response = this.userService.findUserByEmailAndPassword(authenticationDTO.email(), authenticationDTO.password());
            if (Objects.equals(response, "User Not Exist")) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(response);
            } else {
                return ResponseEntity.status(HttpStatus.OK).body(response);
            }
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
