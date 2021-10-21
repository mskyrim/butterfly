package com.butterfly.review.dao;

import com.butterfly.review.model.Review;

import java.util.List;
import java.util.Optional;

public interface IReviewDao {
    void createReview(Review review);
    Optional<Review> getReviewById(String id);
    List<Review> readAllReviews();
    void initReviews(List<Review> reviews);
    void removeAllReviews();
}
