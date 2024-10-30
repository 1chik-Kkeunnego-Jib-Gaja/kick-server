package com.example.kick.domain.review.presentation;

import com.example.kick.domain.review.presentation.dto.ReviewRequest;
import com.example.kick.domain.review.presentation.dto.ReviewResponse;
import com.example.kick.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 작성")
    @PostMapping("/{combination-id}")
    public ReviewResponse create(
        @PathVariable("combination-id") Long combinationId,
        @RequestBody ReviewRequest request
    ) {
        return reviewService.create(combinationId, request.getContent());
    }
}
