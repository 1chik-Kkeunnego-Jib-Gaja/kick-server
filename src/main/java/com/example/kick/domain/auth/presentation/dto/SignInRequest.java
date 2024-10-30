package com.example.kick.domain.auth.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignInRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;
}
