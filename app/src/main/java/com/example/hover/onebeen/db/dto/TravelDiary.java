package com.example.hover.onebeen.db.dto;

public class TravelDiary {
    private Long id;
    private String title = "";
    private String startDate = "";
    private String endDate = "";
    private TravelStatus travelStatus = null;
    private String backgroundImagePath = "";

    public TravelDiary(Long id, String title, String startDate, String endDate, TravelStatus travelStatus, String backgroundImagePath) {
        this.id = id;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.travelStatus = travelStatus;
        this.backgroundImagePath = backgroundImagePath;
    }

    public TravelDiary(String title) {
        this.title = title;
    }

    public TravelDiary(String title, TravelStatus travelStatus) {
        this.title = title;
        this.travelStatus = travelStatus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public TravelStatus getTravelStatus() {
        return travelStatus;
    }

    public void setTravelStatus(TravelStatus travelStatus) {
        this.travelStatus = travelStatus;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    @Override
    public String toString() {
        return "TravelDiary{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", travelStatus=" + travelStatus +
                ", backgroundImagePath='" + backgroundImagePath + '\'' +
                '}';
    }
}
