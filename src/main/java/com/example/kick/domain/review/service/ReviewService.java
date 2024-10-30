package com.example.kick.domain.review.service;

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

    @Transactional
    public ReviewResponse create(String content) {
        User user = userFacade.getCurrentUser();

        Review review = Review.builder()
            .content(content)
            .user(user)
            .build();

        reviewRepository.save(review);

        return new ReviewResponse(review.getId());
    }
}
