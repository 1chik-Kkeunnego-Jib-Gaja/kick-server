package com.example.kick.domain.user.service;

import com.example.kick.domain.user.facade.UserFacade;
import com.example.kick.domain.user.presentation.dto.UserProfileResponse;
import com.example.kick.domain.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserService {

    private final UserFacade userFacade;

    @Transactional
    public UserProfileResponse getUserProfile(String nickname) {
        User user = userFacade.getUserByNickname(nickname);

        return UserProfileResponse.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .build();
    }
}
