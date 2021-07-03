package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ProductInfo2 {
    @SerializedName("produc_detail")
    @Expose
    private ProductDetailitem producDetail;
    @SerializedName("review_list")
    @Expose
    private ArrayList<Review> reviewList = null;
    @SerializedName("related_product")
    @Expose
    private ArrayList<RelatedProduct> relatedProduct = null;
    @SerializedName("total_quantity")
    @Expose
    private Integer totalQuantity;


    public ProductDetailitem getProducDetail() {
        return producDetail;
    }

    public void setProducDetail(ProductDetailitem producDetail) {
        this.producDetail = producDetail;
    }

    public ArrayList<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(ArrayList<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public ArrayList<RelatedProduct> getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(ArrayList<RelatedProduct> relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }



}
