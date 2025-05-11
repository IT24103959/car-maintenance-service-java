package com.service.carservice.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.service.carservice.services.ReviewManagerService;

import com.service.carservice.models.Review;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewManagerService reviewManagerService;

    public ReviewController(ReviewManagerService reviewManagerService) {
        this.reviewManagerService = reviewManagerService;
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody Review review) {
        reviewManagerService.addReview(review);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Review>> getReviews() {
        List<Review> reviews = reviewManagerService.getReviews();
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
