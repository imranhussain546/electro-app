package com.asciitechsolution.electroshop.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;


public class CategoryModel {

    @SerializedName("category_info")
    @Expose
    private ArrayList<CategoryInfo> categoryInfo = null;

    public ArrayList<CategoryInfo> getCategoryInfo() {
        return categoryInfo;
    }

    public void setCategoryInfo(ArrayList<CategoryInfo> categoryInfo) {
        this.categoryInfo = categoryInfo;
    }
}
