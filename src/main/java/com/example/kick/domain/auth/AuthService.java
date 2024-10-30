package com.example.kick.domain.auth;

import com.example.kick.domain.auth.dto.SignInRequest;
import com.example.kick.domain.auth.dto.SignUpRequest;
import com.example.kick.domain.auth.dto.TokenResponse;
import com.example.kick.domain.user.entity.User;
import com.example.kick.domain.user.entity.UserAllergy;
import com.example.kick.domain.user.repo.UserAllergyRepository;
import com.example.kick.domain.user.repo.UserRepository;
import com.example.kick.global.security.jwt.JwtTokenProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final UserAllergyRepository userAllergyRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenResponse signIn(SignInRequest dto) {
        User user = userRepository.findByNickname(dto.getNickname())
            .orElseThrow(() -> new IllegalArgumentException("user not found"));

        String accessToken = jwtTokenProvider.generateAccessToken(user.getNickname());

        return TokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }

    @Transactional
    public TokenResponse signUp(SignUpRequest dto) {
        User user = userRepository.save(User.builder()
                .nickname(dto.getNickname())
                .password(dto.getPassword())
                .eatingStyle(dto.getEatingStyle())
                .goal(dto.getGoal())
            .build());

        userAllergyRepository.saveAll(dto.getAllergy().stream()
                .map(allergy -> UserAllergy.builder()
                        .user(user)
                        .allergy(allergy)
                    .build()).toList());

        String accessToken = jwtTokenProvider.generateAccessToken(user.getNickname());

        return TokenResponse.builder()
            .accessToken(accessToken)
            .build();
    }
}
