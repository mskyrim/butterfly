package com.butterfly.review.service;

import com.butterfly.review.dao.IReviewDao;
import com.butterfly.review.model.Review;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class ReviewService implements IReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private final IReviewDao reviewDao;

    public ReviewService(IReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Override
    public void addReview(Review review) {
        logger.info("Add new review");
        this.reviewDao.createReview(review);
    }

    @Override
    public List<Review> getAll() {
        logger.info("get All reviews");
        return null;
    }

    @Override
    public Review getById(String id) {
        logger.info("Get review by id");
        return this.reviewDao.getReviewById(id).orElse(null);
    }
}
