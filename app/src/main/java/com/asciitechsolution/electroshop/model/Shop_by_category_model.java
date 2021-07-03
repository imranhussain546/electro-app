package com.asciitechsolution.electroshop.model;

public class Shop_by_category_model {

    private int cat_image;
    private  String cat_name,cat_price;
    public Shop_by_category_model(int cat_image, String cat_name, String cat_price) {
        this.cat_image = cat_image;
        this.cat_name = cat_name;
        this.cat_price = cat_price;
    }

    public int getCat_image() {
        return cat_image;
    }

    public void setCat_image(int cat_image) {
        this.cat_image = cat_image;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_price() {
        return cat_price;
    }

    public void setCat_price(String cat_price) {
        this.cat_price = cat_price;
    }
}
