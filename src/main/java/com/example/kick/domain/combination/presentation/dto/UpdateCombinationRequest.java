package com.example.kick.domain.combination.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UpdateCombinationRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String ingredient;

    @NotBlank
    private String recipe;

    private String imageUrl;
}
