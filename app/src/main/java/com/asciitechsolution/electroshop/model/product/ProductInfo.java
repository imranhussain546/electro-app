package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductInfo {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cat_id")
    @Expose
    private String catId;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("image_thumb")
    @Expose
    private String imageThumb;
    @SerializedName("product_details")
    @Expose
    private String productDetails;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("variants")
    @Expose
    private String variants;
    @SerializedName("onsale")
    @Expose
    private String onsale;
    @SerializedName("best_sale")
    @Expose
    private String bestSale;
    @SerializedName("onsale_price")
    @Expose
    private String onsalePrice;
    @SerializedName("image_large_details")
    @Expose
    private String imageLargeDetails;
    @SerializedName("rating")
    @Expose
    private Integer rating;
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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatId() {
        return catId;
    }

    public void setCatId(String catId) {
        this.catId = catId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public String getOnsale() {
        return onsale;
    }

    public void setOnsale(String onsale) {
        this.onsale = onsale;
    }

    public String getBestSale() {
        return bestSale;
    }

    public void setBestSale(String bestSale) {
        this.bestSale = bestSale;
    }

    public String getOnsalePrice() {
        return onsalePrice;
    }

    public void setOnsalePrice(String onsalePrice) {
        this.onsalePrice = onsalePrice;
    }

    public String getImageLargeDetails() {
        return imageLargeDetails;
    }

    public void setImageLargeDetails(String imageLargeDetails) {
        this.imageLargeDetails = imageLargeDetails;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

}
