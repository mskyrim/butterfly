package com.butterfly.review.controller;

import com.butterfly.review.model.Review;
import com.butterfly.review.service.IReviewService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Getter
@AllArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private IReviewService reviewService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public String createReview(@RequestBody Review review) {
        return "" + reviewService.addReview(review);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Review> getAllReviews(){
        return reviewService.getAll();
    }

    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Review getReviewById(@PathVariable("id") String id){
        return reviewService.getById(id);
    }
}
