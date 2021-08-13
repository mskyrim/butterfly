package com.butterfly.review.controller;

import com.butterfly.review.model.Review;
import com.butterfly.review.service.IReviewService;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@NoArgsConstructor
@RequestMapping("/reviews")
public class ReviewController {

    private IReviewService reviewService;

    public ReviewController(IReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String createReview(@RequestBody Review review) {
        return "" + reviewService.addReview(review);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> getAllReviews(){
        return reviewService.getAll();
    }
}
