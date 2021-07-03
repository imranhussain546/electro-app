package com.asciitechsolution.electroshop.model.product;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductModel2 {
    @SerializedName("product_info")
    @Expose
    private ArrayList<ProductInfo2> productInfo2 = null;

    public ArrayList<ProductInfo2> getProductInfo() {
        return productInfo2;
    }

    public void setProductInfo(ArrayList<ProductInfo2> productInfo2) {
        this.productInfo2 = productInfo2;
    }

}

