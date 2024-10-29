package com.example.kick.domain.auth;

import com.example.kick.domain.auth.dto.TokenResponse;
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

    @PostMapping
    public TokenResponse signUp(@RequestBody @Valid String nickname, String password) {
        return authService.signUp(nickname, password);
    }

    @PostMapping("/signin")
    public TokenResponse userSignIn(@RequestBody @Valid String nickname, String password) {
        return authService.signIn(nickname, password);
    }
}
