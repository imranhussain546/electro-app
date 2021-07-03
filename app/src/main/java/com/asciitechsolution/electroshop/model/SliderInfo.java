package com.asciitechsolution.electroshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SliderInfo {

    @SerializedName("slider_link")
    @Expose
    private String sliderLink;
    @SerializedName("slider_image")
    @Expose
    private String sliderImage;
    @SerializedName("slider_position")
    @Expose
    private String sliderPosition;

    public String getSliderLink() {
        return sliderLink;
    }

    public void setSliderLink(String sliderLink) {
        this.sliderLink = sliderLink;
    }

    public String getSliderImage() {
        return sliderImage;
    }

    public void setSliderImage(String sliderImage) {
        this.sliderImage = sliderImage;
    }

    public String getSliderPosition() {
        return sliderPosition;
    }

    public void setSliderPosition(String sliderPosition) {
        this.sliderPosition = sliderPosition;
    }
}
