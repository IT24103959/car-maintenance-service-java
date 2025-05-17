package com.service.carservice.repositories;

import org.springframework.stereotype.Repository;
import com.service.carservice.models.Review;

@Repository
public class ReviewRepository extends BaseRepository<Review> {
    @Override
    protected String getFilePath() {
        return "src/main/resources/data/reviews.json";
    }

    @Override
    protected Class<Review> getTypeClass() {
        return Review.class;
    }

    @Override
    protected int getId(Review review) {
        return review.getId();
    }

}
