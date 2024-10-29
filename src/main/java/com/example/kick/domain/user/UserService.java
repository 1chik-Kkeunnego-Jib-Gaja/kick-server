package com.example.kick.domain.user;

import com.example.kick.domain.user.dto.UserProfileResponse;
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
