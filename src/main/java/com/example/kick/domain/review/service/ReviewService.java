package com.example.kick.domain.review.service;

import com.example.kick.domain.combination.domain.Combination;
import com.example.kick.domain.combination.domain.CombinationRepository;
import com.example.kick.domain.review.domain.Review;
import com.example.kick.domain.review.domain.ReviewRepository;
import com.example.kick.domain.review.presentation.dto.ReviewResponse;
import com.example.kick.domain.user.domain.User;
import com.example.kick.domain.user.facade.UserFacade;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserFacade userFacade;
    private final CombinationRepository combinationRepository;

    @Transactional
    public ReviewResponse create(Long id, String content) {
        User user = userFacade.getCurrentUser();
        Combination combination = combinationRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Combination not found"));

        Review review = Review.builder()
            .content(content)
            .user(user)
            .combination(combination)
            .build();

        reviewRepository.save(review);

        return new ReviewResponse(review.getId());
    }
}
