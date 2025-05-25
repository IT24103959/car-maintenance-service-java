package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;

import com.service.carservice.models.Review;

@Repository
public class ReviewRepository extends BaseRepository<Review> {
    protected String getFilePath() {
        return "src/main/resources/data/reviews.json";
    }

    protected Class<Review> getTypeClass() {
        return Review.class;
    }

    protected int getId(Review review) {
        return review.getId();
    }

}
