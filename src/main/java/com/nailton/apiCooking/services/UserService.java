package com.nailton.apiCooking.services;

import com.nailton.apiCooking.models.User;
import com.nailton.apiCooking.repositories.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getUsers() {
        return (List<User>) this.userRepository.findAll();
    }
}
