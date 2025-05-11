package com.service.carservice.repositories;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
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

    public void persistToFile(List<Review> reviews) {
        try {
            objectMapper.writeValue(Paths.get(FILE_PATH).toFile(), reviews);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addReview(Review review) {
        List<Review> reviews = loadReviews();
        reviews.add(review);
        persistToFile(reviews);
    }

    private List<Review> loadReviews() {
        try {
            return objectMapper.readValue(Files.readAllBytes(Paths.get(FILE_PATH)),
                    objectMapper.getTypeFactory().constructCollectionType(LinkedList.class, Review.class));
        } catch (IOException e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }
}
