package com.nailton.apiCooking.configuration.Util;

import com.nailton.apiCooking.models.User;
import com.nailton.apiCooking.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = requestToken(request);

        if (token != null) {
            String subject = this.userService.validateToken(token);
            User user = this.userService.findUserByEmail(subject);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities()
            );
            SecurityContextHolder.getContext().setAuthentication(authToken);
        }

        filterChain.doFilter(request, response);
    }

   private String requestToken(HttpServletRequest request) {
       String authHeader = request.getHeader("Authorization");
       if (authHeader == null) return null;
       return authHeader.replace("Bearer ", "");
   }
}
