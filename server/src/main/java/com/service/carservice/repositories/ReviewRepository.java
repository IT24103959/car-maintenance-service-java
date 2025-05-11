package com.service.carservice.repositories;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.service.carservice.models.Review;

@Repository
public class ReviewRepository {

    private static final String FILE_PATH = "src/main/resources/data/reviews.json";
    private ObjectMapper objectMapper = new ObjectMapper();

    public List<Review> getAllReviews() {
        return loadReviews();
    }

    public void saveReviews(List<Review> reviews) {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addReview(Review review) {
        List<Review> reviews = loadReviews();
        reviews.add(review);
        saveReviews(reviews);
    }

    private List<Review> loadReviews() {
        try {
            byte[] jsonData = Files.readAllBytes(Paths.get(FILE_PATH));
            return objectMapper.readValue(jsonData,
                    objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, Review.class));
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}
