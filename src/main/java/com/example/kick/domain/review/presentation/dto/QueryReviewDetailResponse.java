package com.example.kick.domain.review.presentation.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QueryReviewDetailResponse {

    private final String content;
    private final Long userId;
}
