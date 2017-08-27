package com.shopspreeng.sardart.Data;

/**
 * Created by jayson surface on 24/08/2017.
 */

public class SarDart {

    public String place, image, text;

    public SarDart() {
    }

    public SarDart(String place, String image, String text) {
        this.place = place;
        this.image = image;
        this.text = text;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "SarDart{" +
                "place='" + place + '\'' +
                ", image='" + image + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
