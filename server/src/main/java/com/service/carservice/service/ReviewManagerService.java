package com.service.carservice.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.service.carservice.models.Review;
import com.service.carservice.models.ServiceRecord;

@Service
public class ReviewManagerService {

    private static final String FILE_PATH = "src/main/resources/data/reviews.txt";
    private LinkedList<Review> reviews;
    private ServiceTrackerService serviceTrackerService;

    public ReviewManagerService() {
        reviews = new LinkedList<>();
        loadReviews();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void addReview(Review review) {
        reviews.add(review);
        saveReviews();
    }

    private void loadReviews() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String reviewText = parts[0];
                int rating = Integer.parseInt(parts[1]);
                int serviceRecordId = Integer.parseInt(parts[2]);
                ServiceRecord serviceRecord = serviceTrackerService.getServiceRecordById(serviceRecordId);
                Review review = new Review(reviewText, rating);
                review.setServiceRecord(serviceRecord);
                reviews.add(review);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveReviews() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Review review : reviews) {
                bw.write(review.getReviewText() + "," + review.getRating() + "," + review.getServiceRecord().getId());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
