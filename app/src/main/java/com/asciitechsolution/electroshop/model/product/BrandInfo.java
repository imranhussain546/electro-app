package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BrandInfo {
    @SerializedName("brand_id")
    @Expose
    private String brandId;
    @SerializedName("brand_name")
    @Expose
    private String brandName;
    @SerializedName("brand_image")
    @Expose
    private String brandImage;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("product_id")
    @Expose
    private String productId;
    @SerializedName("supplier_id")
    @Expose
    private String supplierId;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("product_name")
    @Expose
    private String productName;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("delivery_price")
    @Expose
    private String deliveryPrice;
    @SerializedName("supplier_price")
    @Expose
    private String supplierPrice;
    @SerializedName("unit")
    @Expose
    private String unit;
    @SerializedName("product_model")
    @Expose
    private String productModel;
    @SerializedName("product_details")
    @Expose
    private String productDetails;
    @SerializedName("image_thumb")
    @Expose
    private String imageThumb;

    @SerializedName("variants")
    @Expose
    private String variants;
    @SerializedName("default_variant")
    @Expose
    private String defaultVariant;
    @SerializedName("type")
    @Expose
    private String type;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDeliveryPrice() {
        return deliveryPrice;
    }

    public void setDeliveryPrice(String deliveryPrice) {
        this.deliveryPrice = deliveryPrice;
    }

    public String getSupplierPrice() {
        return supplierPrice;
    }

    public void setSupplierPrice(String supplierPrice) {
        this.supplierPrice = supplierPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getProductModel() {
        return productModel;
    }

    public void setProductModel(String productModel) {
        this.productModel = productModel;
    }

    public String getProductDetails() {
        return productDetails;
    }

    public void setProductDetails(String productDetails) {
        this.productDetails = productDetails;
    }

    public String getImageThumb() {
        return imageThumb;
    }

    public void setImageThumb(String imageThumb) {
        this.imageThumb = imageThumb;
    }

    public String getVariants() {
        return variants;
    }

    public void setVariants(String variants) {
        this.variants = variants;
    }

    public String getDefaultVariant() {
        return defaultVariant;
    }

    public void setDefaultVariant(String defaultVariant) {
        this.defaultVariant = defaultVariant;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBestSale() {
        return bestSale;
    }

    public void setBestSale(String bestSale) {
        this.bestSale = bestSale;
    }

    public String getOnsale() {
        return onsale;
    }

    public void setOnsale(String onsale) {
        this.onsale = onsale;
    }

    public String getOnsalePrice() {
        return onsalePrice;
    }

    public void setOnsalePrice(String onsalePrice) {
        this.onsalePrice = onsalePrice;
    }

    public String getInvoiceDetails() {
        return invoiceDetails;
    }

    public void setInvoiceDetails(String invoiceDetails) {
        this.invoiceDetails = invoiceDetails;
    }

    public String getImageLargeDetails() {
        return imageLargeDetails;
    }

    public void setImageLargeDetails(String imageLargeDetails) {
        this.imageLargeDetails = imageLargeDetails;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getSpecification() {
        return specification;
    }

    public void setSpecification(String specification) {
        this.specification = specification;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTopMenu() {
        return topMenu;
    }

    public void setTopMenu(String topMenu) {
        this.topMenu = topMenu;
    }

    public String getMenuPos() {
        return menuPos;
    }

    public void setMenuPos(String menuPos) {
        this.menuPos = menuPos;
    }

    public String getCatImage() {
        return catImage;
    }

    public void setCatImage(String catImage) {
        this.catImage = catImage;
    }

    public String getCatFavicon() {
        return catFavicon;
    }

    public void setCatFavicon(String catFavicon) {
        this.catFavicon = catFavicon;
    }

    public String getCatType() {
        return catType;
    }

    public void setCatType(String catType) {
        this.catType = catType;
    }

    @SerializedName("best_sale")
    @Expose
    private String bestSale;
    @SerializedName("onsale")
    @Expose
    private String onsale;
    @SerializedName("onsale_price")
    @Expose
    private String onsalePrice;
    @SerializedName("invoice_details")
    @Expose
    private String invoiceDetails;
    @SerializedName("image_large_details")
    @Expose
    private String imageLargeDetails;
    @SerializedName("review")
    @Expose
    private String review;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("tag")
    @Expose
    private String tag;
    @SerializedName("specification")
    @Expose
    private String specification;
    @SerializedName("video")
    @Expose
    private String video;

    @SerializedName("parent_category_id")
    @Expose
    private String parentCategoryId;
    @SerializedName("category_name")
    @Expose
    private String categoryName;
    @SerializedName("top_menu")
    @Expose
    private String topMenu;
    @SerializedName("menu_pos")
    @Expose
    private String menuPos;
    @SerializedName("cat_image")
    @Expose
    private String catImage;
    @SerializedName("cat_favicon")
    @Expose
    private String catFavicon;
    @SerializedName("cat_type")
    @Expose
    private String catType;

    public String getBrandId() {
        return brandId;
    }

    public void setBrandId(String brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getBrandImage() {
        return brandImage;
    }

    public void setBrandImage(String brandImage) {
        this.brandImage = brandImage;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
