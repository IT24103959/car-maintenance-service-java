package com.service.carservice.services;

import org.springframework.stereotype.Service;

import com.service.carservice.cache.ReviewList;
import com.service.carservice.models.Review;
import com.service.carservice.repositories.ReviewRepository;

@Service
public class ReviewService extends BaseService {
    private final ReviewRepository reviewRepository;
    private ReviewList items;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
        items = reviewRepository.getAll();
    }

    public void addReview(Review review) {
        review.setId(reviewRepository.getNextId(true));
        items.insert(review);
    }

    public Review getReviewById(int id) {
        return getById(id);
    }

    public boolean updateReviewById(int id, Review updatedReview) {
        updatedReview.setId(id);
        return updateById(id, updatedReview);
    }

    public boolean updateById(int id, Review updated) {
        for (int i = 0; i < items.getSize(); i++) {
            if (items.getByIndex(i).getId() == id) {
                items.setByIndex(i, updated);
                return true;
            }
        }
        return false;
    }

    public Review getById(int id) {
        for (int i = 0; i < items.getSize(); i++) {
            Review item = items.getByIndex(i);
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }

    public void deleteById(int id) {
        Review value = getById(id);
        items.deleteByValue(value);
    }

    public Review[] getAll() {
        return items.toArray();
    }

    protected void persistOnShutdown() {
        reviewRepository.persistToFile(items);
    }

}