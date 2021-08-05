package com.butterfly.review.controller;

import com.butterfly.review.model.Review;
import com.butterfly.review.service.IReviewService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor
public class ReviewController {

    private final IReviewService reviewService;

    public ReviewController(IReviewService reviewService){
        this.reviewService = reviewService;
    }

    public void createReview(Review review) {
        reviewService.addReview(review);
    }
}
