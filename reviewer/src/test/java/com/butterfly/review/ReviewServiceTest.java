package com.butterfly.review;

import com.butterfly.review.model.Review;
import com.butterfly.review.service.IReviewService;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
}
