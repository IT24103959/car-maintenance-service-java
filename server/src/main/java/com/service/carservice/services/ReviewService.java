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

    public Review[] getAllReviews() {
        Review[] itemsArr = new Review[items.size()];
        for (int i = 0; i < items.size(); i++) {
            itemsArr[i] = items.get(i);
        }
        return itemsArr;
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

    protected int getId(Review review) {
        return review.getId();
    }

    protected void persistOnShutdown() {
        reviewRepository.persistToFile(items);
    }

}