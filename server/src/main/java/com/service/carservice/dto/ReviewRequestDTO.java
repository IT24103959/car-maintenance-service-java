package com.service.carservice.dto;

import com.service.carservice.models.Review;
import com.service.carservice.models.ServiceRecord;

public class ReviewRequestDTO {
    private Review review;
    private ServiceRecord serviceRecord;

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public ServiceRecord getServiceRecord() {
        return serviceRecord;
    }

    public void setServiceRecord(ServiceRecord serviceRecord) {
        this.serviceRecord = serviceRecord;
    }
}