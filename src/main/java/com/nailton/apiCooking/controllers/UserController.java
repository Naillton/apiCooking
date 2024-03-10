package com.nailton.apiCooking.controllers;

import com.nailton.apiCooking.configuration.Util.Middlewares;
import com.nailton.apiCooking.dto.UserDTO;
import com.nailton.apiCooking.models.User;
import com.nailton.apiCooking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/users")
    public ResponseEntity<List<User>> getUsers() {
        try {
            List<User> users = this.userService.getUsers();
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            return  ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping(value = "/user/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email) {
        try {
            User user = this.userService.findUserByEmail(email);
            return ResponseEntity.status(HttpStatus.OK).body(user);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<String> updateUser(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable UUID id, @RequestBody UserDTO userDTO) {
        User user = this.userService.findUserById(id);
        String responseToken = this.userService.validateToken(token);
        String responseTokenError = "Invalid Credentials";
        String userNotFound = "User Not Found";
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(userNotFound);
        } else if(!Objects.equals(user.getEmail(), responseToken)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseTokenError);
        } else {
            try {
                String errorFields = "Invalid Fields";
                boolean isTrue = Middlewares.validCredentials(userDTO.email(), userDTO.password());
                if (isTrue) {
                    this.userService.updateUser(userDTO.toEntity(), id);
                    return ResponseEntity.status(HttpStatus.OK).body("User updated");
                }
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(errorFields);
            } catch (Exception e) {
                return ResponseEntity.internalServerError().build();
            }
        }
    }

    @DeleteMapping(value = "/user/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable UUID id) {
        try {
            this.userService.deleteUser(id);
            return ResponseEntity.status(HttpStatus.OK).body("User deleted");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
