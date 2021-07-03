package com.asciitechsolution.electroshop.model;

public class OfferModel {

    private int offer_image;
    private String offer_des;

    public OfferModel(int offer_image, String offer_des) {
        this.offer_image = offer_image;
        this.offer_des = offer_des;
    }

    public int getOffer_image() {
        return offer_image;
    }

    public void setOffer_image(int offer_image) {
        this.offer_image = offer_image;
    }

    public String getOffer_des() {
        return offer_des;
    }

    public void setOffer_des(String offer_des) {
        this.offer_des = offer_des;
    }
}
