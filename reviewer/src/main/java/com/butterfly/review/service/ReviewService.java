package com.butterfly.review.service;

import com.butterfly.review.dao.IReviewDao;
import com.butterfly.review.model.Review;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Service
@Getter
public class ReviewService implements IReviewService {
    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    private final IReviewDao reviewDao;

    public ReviewService(IReviewDao reviewDao){
        this.reviewDao = reviewDao;
    }

    @Override
    public boolean addReview(Review review) {
        logger.info("Add new review");
        this.reviewDao.createReview(review);
        return true;
    }

    @Override
    public List<Review> getAll() {
        logger.info("get All reviews");
        return this.reviewDao.readAllReviews();
    }

    @Override
    public Review getById(String id) {
        logger.info("Get review by id");
        return this.reviewDao.getReviewById(id).orElse(null);
    }

    @PostConstruct
    private void initService(){
        logger.info("--------- initService ----------");
        byte val = (byte) (Math.random()*5);
        List<Review> reviews = IntStream.of(1,2,3,4)
                .mapToObj(id -> new Review(id+"", (byte) (Math.random()*5), "Review for article: " + id))
                .collect(Collectors.toList());
        this.reviewDao.initReviews(reviews);
    }

    @Override
    public void initReviews(List<Review> reviews) {
        this.reviewDao.initReviews(reviews);
    }

    @Override
    public void removeAllReviews() {
        this.reviewDao.removeAllReviews();
    }
}
