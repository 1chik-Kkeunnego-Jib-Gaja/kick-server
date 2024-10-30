package com.example.kick.domain.auth.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {

    private final String accessToken;

}
