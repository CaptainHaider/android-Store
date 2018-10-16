package com.example.bilalchips.store_app;

/**
 * Created by bilalchips on 11/26/2017.
 */

public class skateboards {
    private   String Brandname;
    private   int size;
    private int price;
    private String imgurl;

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getBrandname() {
        return Brandname;
    }

    public void setBrandname(String brandname) {
        Brandname = brandname;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
