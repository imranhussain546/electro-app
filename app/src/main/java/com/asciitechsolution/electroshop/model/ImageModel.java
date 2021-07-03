package com.asciitechsolution.electroshop.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageModel {

    @SerializedName("image_info")
    @Expose
    private ArrayList<ImageInfo> imageInfo = null;

    public ArrayList<ImageInfo> getImageInfo() {
        return imageInfo;
    }

    public void setImageInfo(ArrayList<ImageInfo> imageInfo) {
        this.imageInfo = imageInfo;
    }
}
