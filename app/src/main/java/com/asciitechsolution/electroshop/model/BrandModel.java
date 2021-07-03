package com.asciitechsolution.electroshop.model;

import com.asciitechsolution.electroshop.model.product.BrandInfo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BrandModel {
    @SerializedName("brand_info")
    @Expose
    private ArrayList<BrandInfo> brandInfo = null;

    public ArrayList<BrandInfo> getBrandInfo() {
        return brandInfo;
    }

    public void setBrandInfo(ArrayList<BrandInfo> brandInfo) {
        this.brandInfo = brandInfo;
    }

}
