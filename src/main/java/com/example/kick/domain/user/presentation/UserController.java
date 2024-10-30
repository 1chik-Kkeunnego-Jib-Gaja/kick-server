package com.example.kick.domain.user.presentation;

import com.example.kick.domain.user.presentation.dto.UserProfileResponse;
import com.example.kick.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/users")
@RestController
public class UserController {

    private final UserService userService;

    @Operation(summary = "마이페이지 조회")
    @GetMapping("/my")
    public UserProfileResponse getUserProfile() {
        return userService.getUserProfile();
    }
}
