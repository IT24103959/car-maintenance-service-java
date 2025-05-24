package com.service.carservice.models;

public class Review {
    private int serviceId; // Replacing ServiceRecord object with serviceId
    String reviewText;
    int rating;
    int id;

    public Review() {

    }

    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public Review(int serviceId, String reviewText, int rating) {
        this.serviceId = serviceId;
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
