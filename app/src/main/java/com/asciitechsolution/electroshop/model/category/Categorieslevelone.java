package com.asciitechsolution.electroshop.model.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Categorieslevelone {
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("categoriesleveltwo")
    @Expose
    private ArrayList<Object> categoriesleveltwo = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ArrayList<Object> getCategoriesleveltwo() {
        return categoriesleveltwo;
    }

    public void setCategoriesleveltwo(ArrayList<Object> categoriesleveltwo) {
        this.categoriesleveltwo = categoriesleveltwo;
    }

}
