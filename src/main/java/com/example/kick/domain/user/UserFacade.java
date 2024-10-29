package com.example.kick.domain.user;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Component
public class UserFacade {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String nickname = SecurityContextHolder.getContext().getAuthentication().getName();
        return getUserByNickname(nickname);
    }

    public User getUserByNickname(String nickname) {
        return userRepository.findByNickname(nickname)
            .orElseThrow(() -> new NoSuchElementException("user not found"));
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
            .orElseThrow(() -> new NoSuchElementException("user not found"));
    }
}
