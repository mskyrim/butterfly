package com.butterfly.review.dao;

import com.butterfly.review.model.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ReviewDaoMemory implements IReviewDao{
    private final List<Review> reviews = new ArrayList<>();

    @Override
    public void createReview(Review review) {
        this.reviews.add(review);
    }

    @Override
    public Optional<Review> getReviewById(String id) {
        return this.reviews.stream().filter(review -> review.getId().equals(id)).findFirst();
    }

    @Override
    public List<Review> readAllReviews() {
        return this.reviews;
    }

    @Override
    public void initReviews(List<Review> reviews) {
        this.reviews.addAll(reviews);
    }

    @Override
    public void removeAllReviews() {
        this.reviews.clear();
    }


}
