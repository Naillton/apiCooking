package com.nailton.apiCooking.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.nailton.apiCooking.models.User;
import com.nailton.apiCooking.repositories.UserRepository;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${api.security.token.secret}")
    private String secret;

    public User findUserByEmail(String email) {
        return this.userRepository.findUserByEmail(email);
    }

    public void insertUser(User user) {
        this.userRepository.save(user);
    }

    public List<User> getUsers() {
        return (List<User>) this.userRepository.findAll();
    }

    public String findUserByEmailAndPassword(String email, String password) {
        User user = this.userRepository.findUserByEmailAndPassword(email, password);
        if (user != null) {
            return generateToken(user);
        } else {
            return "User Not Exist";
        }
    }

    private String generateToken(User user) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.create()
                .withIssuer("API_Cooking")
                .withSubject(user.getEmail())
                .withExpiresAt(expirationDate())
                .sign(algorithm);
    }

    private Instant expirationDate() {
        return LocalDateTime.now()
                .plusHours(3)
                .toInstant(ZoneOffset.of("-03:00"));
    }

    public String validateToken(String token) {
        Algorithm algorithm = Algorithm.HMAC256(secret);
        return JWT.require(algorithm)
                .withIssuer("API_Cooking")
                .build()
                .verify(token)
                .getSubject();
    }
}
