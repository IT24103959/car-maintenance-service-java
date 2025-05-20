package com.service.carservice.services;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Review;
import com.service.carservice.repositories.ReviewRepository;

@Service
public class ReviewService extends BaseService<Review> {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        super(reviewRepository.getAll());
        this.reviewRepository = reviewRepository;
    }

    public void addReview(Review review) {
        review.setId(reviewRepository.getNextId(true));
        items.add(review);
    }

    public Review getReviewById(int id) {
        return getById(id);
    }

    public boolean updateReviewById(int id, Review updatedReview) {
        updatedReview.setId(id);
        return updateById(id, updatedReview);
    }

    @Override
    protected int getId(Review review) {
        return review.getId();
    }

    @Override
    protected void persistOnShutdown() {
        reviewRepository.persistToFile(items);
    }

}