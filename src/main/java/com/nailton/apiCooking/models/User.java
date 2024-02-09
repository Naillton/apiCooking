package com.nailton.apiCooking.models;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.UUID;
import java.util.List;

@Entity
@Transactional
@Table(name = "user")
public class User implements UserDetails {

    @Id
    private final UUID id;

    @Column(unique = true)
    private String email;

    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_user", referencedColumnName = "id")
    private List<Revenues> revenuesList;


    public User() {
        this.id = UUID.randomUUID();
    }

    public User(String email, String password) {
        this.id = UUID.randomUUID();
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public List<Revenues> getRevenuesList() {
        return revenuesList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addRevenues(Revenues revenues) {
        revenuesList.add(revenues);
    }

    public void setRevenuesList(List<Revenues> revenuesList) {
        this.revenuesList = revenuesList;
    }
}
