package com.asciitechsolution.electroshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ImageInfo {
    @SerializedName("image_gallery_id")
    @Expose
    private String imageGalleryId;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("img_thumb")
    @Expose
    private String imgThumb;

    public String getImageGalleryId() {
        return imageGalleryId;
    }

    public void setImageGalleryId(String imageGalleryId) {
        this.imageGalleryId = imageGalleryId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImgThumb() {
        return imgThumb;
    }

    public void setImgThumb(String imgThumb) {
        this.imgThumb = imgThumb;
    }
}
