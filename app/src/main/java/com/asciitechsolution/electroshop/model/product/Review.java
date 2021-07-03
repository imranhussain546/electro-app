package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Review {
    @SerializedName("product_review_id")
    @Expose
    private String productReviewId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("reviewer_id")
    @Expose
    private String reviewerId;
    @SerializedName("comments")
    @Expose
    private String comments;
    @SerializedName("rate")
    @Expose
    private String rate;
    @SerializedName("date_time")
    @Expose
    private String dateTime;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("customer_name")
    @Expose
    private String customerName;

    public String getProductReviewId() {
        return productReviewId;
    }

    public void setProductReviewId(String productReviewId) {
        this.productReviewId = productReviewId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getReviewerId() {
        return reviewerId;
    }

    public void setReviewerId(String reviewerId) {
        this.reviewerId = reviewerId;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

}
