package com.example.kick.domain.user.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponse {

    private final Long userId;
    private final String nickname;

}
