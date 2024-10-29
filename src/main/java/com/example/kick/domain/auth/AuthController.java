package com.example.kick.domain.auth;

import com.example.kick.domain.auth.dto.SignInRequest;
import com.example.kick.domain.auth.dto.SignUpRequest;
import com.example.kick.domain.auth.dto.TokenResponse;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "회원가입")
    @PostMapping
    public TokenResponse signUp(@RequestBody @Valid SignUpRequest request) {
        return authService.signUp(request.getNickname(), request.getPassword());
    }

    @Operation(summary = "로그인")
    @PostMapping("/signin")
    public TokenResponse userSignIn(@RequestBody @Valid SignInRequest request) {
        return authService.signIn(request.getNickname(), request.getPassword());
    }
}
