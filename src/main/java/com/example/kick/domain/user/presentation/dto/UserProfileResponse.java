package com.example.kick.domain.user.presentation.dto;

import com.example.kick.domain.user.domain.type.Allergy;
import com.example.kick.domain.user.domain.type.EatingStyle;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class UserProfileResponse {

    private final Long userId;
    private final String nickname;
    private final List<Allergy> allergies;
    private final EatingStyle eatingStyle;
    private final String goal;
}
