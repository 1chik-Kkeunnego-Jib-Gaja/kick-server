package com.example.kick.domain.combination.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class CreateCombinationRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String ingredient;

    @NotBlank
    private String recipe;

    private String imageUrl;

    private List<String> tags;
}
