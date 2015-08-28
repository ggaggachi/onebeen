package com.example.hover.onebeen.diarylist;

public class TravelDiaryListItem {
    private Long id;
    private String tripTitle;
    private String tripStartDate;
    private String tripEndDate;
    private String subTitle;

    public TravelDiaryListItem(String tripTitle, String tripStartDate, String tripEndDate, String subTitle, Long id) {
        this.tripTitle = tripTitle;
        this.tripStartDate = tripStartDate;
        this.tripEndDate = tripEndDate;
        this.subTitle = subTitle;
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TravelDiaryListItem{" +
                "id=" + id +
                ", tripTitle='" + tripTitle + '\'' +
                ", tripStartDate='" + tripStartDate + '\'' +
                ", tripEndDate='" + tripEndDate + '\'' +
                ", subTitle='" + subTitle + '\'' +
                '}';
    }
}
