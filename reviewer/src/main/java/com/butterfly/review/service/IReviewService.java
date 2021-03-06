package com.butterfly.review.service;

import com.butterfly.review.model.Review;

import java.util.List;

public interface IReviewService {
    boolean addReview(Review review);
    List<Review> getAll();
    Review getById(String id);
    void initReviews(List<Review> reviews);
    void removeAllReviews();
}
