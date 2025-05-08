package com.service.carservice.models;

import com.service.carservice.services.ServiceTrackerService;

public class Review {
    ServiceRecord serviceRecord;
    String reviewText;
    int rating;

    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }

    public void setServiceRecord(ServiceRecord serviceRecord) {
        ServiceTrackerService serviceTrackerService = new ServiceTrackerService();
        serviceRecord = serviceTrackerService.getServiceRecordById(serviceRecord.getId());
        this.serviceRecord = serviceRecord;
    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public int getRating() {
        return rating;
    }

    public String getReviewText() {
        return reviewText;
    }
}
