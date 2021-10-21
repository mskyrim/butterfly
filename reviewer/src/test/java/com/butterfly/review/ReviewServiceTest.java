package com.butterfly.review;

import com.butterfly.review.model.Review;
import com.butterfly.review.service.IReviewService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ReviewServiceTest {

    @SpyBean
    private IReviewService reviewService;


    @Test
    public void should_add_review() {
        // Given
        byte rating = 4;
        String review_for_dummy_article = "Review for dummy article";
        Review review = new Review("1", rating, review_for_dummy_article);
        // When
        reviewService.removeAllReviews();
        reviewService.addReview(review);
        // Then
        Review resultReview = reviewService.getById("1");
        SoftAssertions softAssertions =new SoftAssertions();
        softAssertions.assertThat(resultReview).isNotNull();
        softAssertions.assertThat(resultReview.getId()).isEqualTo("1");
        softAssertions.assertThat(resultReview.getRating()).isEqualTo(rating);
        softAssertions.assertThat(resultReview.getComment()).isEqualTo(review_for_dummy_article);
        softAssertions.assertAll();
    }

    @Test
    public void should_return_review(){
        // When
        reviewService.removeAllReviews();
        List<Review> reviews = initReview();
        // When
        List<Review> result = reviewService.getAll();
        // Then
        SoftAssertions softAssertions =new SoftAssertions();
        softAssertions.assertThat(result).isNotNull();
        softAssertions.assertThat(result).isNotEmpty();
        softAssertions.assertThat(result).isEqualTo(reviews);
        softAssertions.assertThat(result.size()).isEqualTo(4);
        softAssertions.assertAll();
    }

    private List<Review> initReview(){
        byte val = (byte) (Math.random()*5);
        List<Review> reviews = IntStream.of(1,2,3,4)
                .mapToObj(id -> new Review(id+"", (byte) (Math.random()*5), "Review for article: " + id))
                .collect(Collectors.toList());
        reviewService.initReviews(reviews);
        return reviews;
    }
}
