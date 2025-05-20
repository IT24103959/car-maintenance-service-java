package com.service.carservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.carservice.models.Review;
import com.service.carservice.services.ReviewService;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController extends BaseController<Review> {

    @Autowired
    private ReviewService reviewService;

    @GetMapping
    public ResponseEntity<Review[]> getReviews() {
        return response(reviewService.getAll(), Review.class);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReviewById(@PathVariable int id) {
        return response(reviewService.getById(id));
    }

    @PostMapping
    public ResponseEntity<Void> addReview(@RequestBody Review review) {
        reviewService.addReview(review);
        return response(HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateReview(@PathVariable int id, @RequestBody Review updatedReview) {
        if (reviewService.updateReviewById(id, updatedReview)) {
            return response(HttpStatus.NO_CONTENT);
        } else {
            return response(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReview(@PathVariable int id) {
        reviewService.deleteById(id);
        return response(HttpStatus.NO_CONTENT);
    }
}
