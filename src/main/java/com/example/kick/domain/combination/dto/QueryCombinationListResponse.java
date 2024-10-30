package com.example.kick.domain.combination.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class QueryCombinationListResponse {

    private final List<CombinationResponse> combinationList;

    @Getter
    @Builder
    public static class CombinationResponse {
        private final Long id;
        private final String name;
        private final String imageUrl;
        private final List<String> tags;
    }
}
