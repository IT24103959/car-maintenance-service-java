// package com.service.carservice.controller;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;

// import com.service.carservice.service.ReviewManagerService;

// import com.service.carservice.models.Review;
// import com.service.carservice.dto.ReviewRequestDTO;

// @CrossOrigin(origins = "http://localhost:3000")
// @RestController
// @RequestMapping("/api/reviews")
// public class ReviewController {

// @Autowired
// private ReviewManagerService reviewManagerService;

// @PostMapping
// public ResponseEntity<Void> addReview(@RequestBody ReviewRequestDTO
// requestDTO) {
// Review review = requestDTO.getReview();

// review.setServiceRecord(requestDTO.getServiceRecord());

// reviewManagerService.addReview(review);
// return new ResponseEntity<>(HttpStatus.CREATED);
// }

// @GetMapping
// public ResponseEntity<List<Review>> getReviews() {
// List<Review> reviews = reviewManagerService.getReviews();
// return new ResponseEntity<>(reviews, HttpStatus.OK);
// }
// }
