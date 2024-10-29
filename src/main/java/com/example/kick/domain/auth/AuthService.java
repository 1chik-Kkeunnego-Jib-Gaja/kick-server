package com.example.kick.domain.auth;

import com.example.kick.domain.auth.dto.TokenResponse;
import com.example.kick.domain.user.User;
import com.example.kick.domain.user.UserRepository;
import com.example.kick.global.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(String nickname, String password) {
        User user = userRepository.findByNickname(nickname)
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        String accessToken = jwtTokenProvider.generateAccessToken(user.getNickname());

        return TokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }

    @Transactional
    public TokenResponse signUp(String nickname, String password) {
        User user = userRepository.save(User.builder()
            .nickname(nickname)
            .password(password)
            .build());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getNickname());

        return TokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }
}
