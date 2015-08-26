package com.example.hover.onebeen.diary;

public class TravelDiaryItem {
    private String tripTitle;
    private String tripStartDate;
    private String tripEndDate;
    private String subTitle;

    public TravelDiaryItem(String tripTitle, String tripStartDate, String tripEndDate, String subTitle) {
        this.tripTitle = tripTitle;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.subTitle = subTitle;
    }

    public String getTripTitle() {
        return tripTitle;
    }

    public void setTripTitle(String tripTitle) {
        this.tripTitle = tripTitle;
    }

    public String getTripStartDate() {
        return tripStartDate;
    }

    public void setTripStartDate(String tripStartDate) {
        this.tripStartDate = tripStartDate;
    }

    public String getTripEndDate() {
        return tripEndDate;
    }

    public void setTripEndDate(String tripEndDate) {
        this.tripEndDate = tripEndDate;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
}
