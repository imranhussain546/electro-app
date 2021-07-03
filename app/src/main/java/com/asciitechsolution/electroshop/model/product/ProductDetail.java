package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductDetail {
    @SerializedName("produc_detail")
    @Expose
    private ProductDetailitem producDetail;
    @SerializedName("review_list")
    @Expose
    private List<Object> reviewList = null;
    @SerializedName("related_product")
    @Expose
    private List<RelatedProduct> relatedProduct = null;
    @SerializedName("total_quantity")
    @Expose
    private Integer totalQuantity;

    public ProductDetailitem getProducDetail() {
        return producDetail;
    }

    public void setProducDetail(ProductDetailitem producDetail) {
        this.producDetail = producDetail;
    }

    public List<Object> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Object> reviewList) {
        this.reviewList = reviewList;
    }

    public List<RelatedProduct> getRelatedProduct() {
        return relatedProduct;
    }

    public void setRelatedProduct(List<RelatedProduct> relatedProduct) {
        this.relatedProduct = relatedProduct;
    }

    public Integer getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Integer totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
