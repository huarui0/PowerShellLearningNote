package com.lzsoft.lzdata.mobile.purex.persistencedata.entity;

import java.io.Serializable;

/**
 * Created by javierg on 12/10/2016.
 */

public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    private int id;

    private String title;

    private String shortdesc;

    private double price;

    private double rating;

    private String image;

    public Product() {

    }

    public Product(int id, String title, String shortdesc, double rating, double price, String image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.price = price;
        this.rating = rating;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public void setShortdesc(String shortdesc) {
        this.shortdesc = shortdesc;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void  setValue() {

    }
}
