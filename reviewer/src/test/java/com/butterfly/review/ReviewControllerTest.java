package com.butterfly.review;

import com.butterfly.review.controller.ReviewController;
import com.butterfly.review.model.Review;
import com.butterfly.review.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpStatus;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
public class ReviewControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ReviewService reviewService;

    private JacksonTester<Review> jsonReview;

    @InjectMocks
    private ReviewController reviewController;

    @BeforeEach
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
        // MockMvc standalone approach
        mockMvc = MockMvcBuilders.standaloneSetup(reviewController).build();
    }

    @Test
    public void should_return_all_review() throws Exception {
        // Given
        byte val = (byte) (Math.random() * 5);
        List<Review> reviews = IntStream.of(1, 2, 3, 4)
                .mapToObj(id -> new Review(id + "", (byte) (Math.random() * 5), "Review for article: " + id))
                .collect(Collectors.toList());
        BDDMockito.given(reviewService.getAll()).willReturn(reviews);
        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/reviews").accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andReturn().getResponse();
        // Then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
        softAssertions.assertThat(response.getContentAsString()).isNotEmpty();
        softAssertions.assertThat(response.getContentAsString()).isEqualTo(reviews.stream().map(review -> review.toString()).toArray());
    }

    @Test
    public void should_return_review_by_id() throws Exception {
        // Given
        byte val = (byte) (Math.random() * 5);
        List<Review> reviews = IntStream.of(1, 2, 3, 4)
                .mapToObj(id -> new Review(id + "", (byte) id, "Review for article: " + id))
                .collect(Collectors.toList());
        reviewService.initReviews(reviews);
        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .get("/reviews/1").accept(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print()).andReturn().getResponse();
        // Then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_OK);
        softAssertions.assertThat(response.getContentAsString()).isEqualTo(jsonReview.write(new Review("1",(byte) 1,"Review for article: 1")).getJson());
    }

    @Test
    public void should_create_review() throws Exception {
        // Given
        Review review = new Review("1", (byte) 5, "Review for new article");
        // When
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders
                .post("/reviews").contentType(MediaType.APPLICATION_JSON_VALUE).content(jsonReview.write(review).getJson()))
                .andReturn().getResponse();

        // Then
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(response.getStatus()).isEqualTo(HttpStatus.SC_CREATED);
    }

}
