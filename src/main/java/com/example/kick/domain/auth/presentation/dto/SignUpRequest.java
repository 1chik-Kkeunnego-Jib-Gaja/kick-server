package com.example.kick.domain.auth.presentation.dto;

import com.example.kick.domain.user.domain.type.Allergy;
import com.example.kick.domain.user.domain.type.EatingStyle;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    private List<Allergy> allergy;

    private EatingStyle eatingStyle;

    @NotBlank
    private String goal;

}
