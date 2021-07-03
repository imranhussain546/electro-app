package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductModel {
    @SerializedName("product_info")
    @Expose
    private ArrayList<ProductInfo> productInfo = null;

    public ArrayList<ProductInfo> getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(ArrayList<ProductInfo> productInfo) {
        this.productInfo = productInfo;
    }

}
