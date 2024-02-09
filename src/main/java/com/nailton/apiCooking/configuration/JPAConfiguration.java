package com.nailton.apiCooking.configuration;

import com.nailton.apiCooking.services.RevenuesService;
import com.nailton.apiCooking.services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.nailton.apiCooking.repositories")
public class JPAConfiguration {

    @Bean("userService")
    public UserService userService() {
        return new UserService();
    }

    @Bean("revenuesService")
    public RevenuesService revenuesService() {
        return new RevenuesService();
    }
}
