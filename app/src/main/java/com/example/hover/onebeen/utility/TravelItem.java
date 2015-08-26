package com.example.hover.onebeen.utility;

/**
 * Created by Dark on 2015. 8. 26..
 */
public class TravelItem {
    private int startLineImage;
    private int travelCountImage;
    private String title;
    private String date;

    public TravelItem(int startLineImage, int travelCountImage, String title, String date) {
        this.startLineImage = startLineImage;
        this.travelCountImage = travelCountImage;
        this.title = title;
        this.date = date;
    }

    public int getStartLineImage() {
        return startLineImage;
    }

    public void setStartLineImage(int startLineImage) {
        this.startLineImage = startLineImage;
    }

    public int getTravelCountImage() {
        return travelCountImage;
    }

    public void setTravelCountImage(int travelCountImage) {
        this.travelCountImage = travelCountImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
