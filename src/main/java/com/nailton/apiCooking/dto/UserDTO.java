package com.nailton.apiCooking.dto;

import com.nailton.apiCooking.models.User;

public record UserDTO(String email, String password) {
    public User toEntity() {
        return new User(email, password);
    }
}
