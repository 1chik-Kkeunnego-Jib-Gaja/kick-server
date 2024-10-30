package com.example.kick.domain.user.service;

import com.example.kick.domain.user.domain.UserAllergyRepository;
import com.example.kick.domain.user.domain.type.Allergy;
import com.example.kick.domain.user.facade.UserFacade;
import com.example.kick.domain.user.presentation.dto.UserProfileResponse;
import com.example.kick.domain.user.domain.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserFacade userFacade;
    private final UserAllergyRepository userAllergyRepository;

    @Transactional
    public UserProfileResponse getUserProfile() {
        User user = userFacade.getCurrentUser();
        List<Allergy> allergies = userAllergyRepository.findAllergiesByUserId(user.getId());

        return UserProfileResponse.builder()
            .userId(user.getId())
            .nickname(user.getNickname())
            .allergies(allergies)
            .eatingStyle(user.getEatingStyle())
            .goal(user.getGoal())
            .build();
    }
}
