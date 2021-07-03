package com.asciitechsolution.electroshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class BestSaleModel {


    @SerializedName("best_sale_info")
    @Expose
    private ArrayList<BestSaleInfo> bestSaleInfo = null;

    public ArrayList<BestSaleInfo> getBestSaleInfo() {
        return bestSaleInfo;
    }

    public void setBestSaleInfo(ArrayList<BestSaleInfo> bestSaleInfo) {
        this.bestSaleInfo = bestSaleInfo;
    }
}

