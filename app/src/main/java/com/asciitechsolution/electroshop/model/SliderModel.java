package com.asciitechsolution.electroshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SliderModel {

    @SerializedName("slider_info")
    @Expose
    private ArrayList<SliderInfo> sliderInfo = null;

    public ArrayList<SliderInfo> getSliderInfo() {
        return sliderInfo;
    }

    public void setSliderInfo(ArrayList<SliderInfo> sliderInfo) {
        this.sliderInfo = sliderInfo;
    }

}
