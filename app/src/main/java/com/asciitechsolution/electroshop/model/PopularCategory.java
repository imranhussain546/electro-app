package com.asciitechsolution.electroshop.model;

public class PopularCategory {
    private String  categoryName;
    private int CategoryIconLink;
    public PopularCategory(int categoryIconLink, String categoryName) {
        CategoryIconLink = categoryIconLink;
        this.categoryName = categoryName;
    }

    public int getCategoryIconLink() {
        return CategoryIconLink;
    }

    public void setCategoryIconLink(int categoryIconLink) {
        CategoryIconLink = categoryIconLink;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
