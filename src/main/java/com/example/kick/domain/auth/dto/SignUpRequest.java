package com.example.kick.domain.auth.dto;

import com.example.kick.domain.user.entity.type.Allergy;
import com.example.kick.domain.user.entity.type.EatingStyle;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SignUpRequest {

    @NotBlank
    private String nickname;

    @NotBlank
    private String password;

    private Allergy allergy;

    private EatingStyle eatingStyle;

    @NotBlank
    private String goal;

}
