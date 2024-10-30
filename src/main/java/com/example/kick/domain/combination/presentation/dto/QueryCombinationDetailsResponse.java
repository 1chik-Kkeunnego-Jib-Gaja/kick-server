package com.example.kick.domain.combination.presentation.dto;

import com.example.kick.domain.review.presentation.dto.QueryReviewDetailResponse;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class QueryCombinationDetailsResponse {

    private final String name;
    private final String ingredient;
    private final String recipe;
    private final String imageUrl;
    private final Long userId;
    private final List<String> tags;
    private final long likeCount;
    private final List<QueryReviewDetailResponse> reviews;
}
