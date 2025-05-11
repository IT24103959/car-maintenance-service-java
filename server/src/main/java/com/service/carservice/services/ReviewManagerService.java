package com.service.carservice.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Review;
import com.service.carservice.repositories.ReviewRepository;

@Service
public class ReviewManagerService {

    private final ReviewRepository reviewRepository;

    public ReviewManagerService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Review review) {
        reviewRepository.addReview(review);
    }

    public List<Review> getReviews() {
        return reviewRepository.getAllReviews();
    }

}