package com.service.carservice.services;

import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Review;
import com.service.carservice.repositories.ReviewRepository;

@Service
public class ReviewManagerService {

    private final ReviewRepository reviewRepository;
    private LinkedList<Review> reviews;

    public ReviewManagerService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        this.reviews = new LinkedList<>(reviewRepository.getAllReviews());
    }

    public void addReview(Review review) {
        reviews.add(review);
        reviewRepository.addReview(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

}