package com.example.kick.global.security.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface AuthDetails {
    UserDetails loadUserByUsername(String username);
}
