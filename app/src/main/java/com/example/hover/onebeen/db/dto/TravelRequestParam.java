package com.example.hover.onebeen.db.dto;

public class TravelRequestParam {
    private Integer _id;
    private String userId;
    private String travelId;
    private String puzzleId;

    public TravelRequestParam(Integer _id, String userId, String travelId, String puzzleId) {
        this._id = _id;
        this.userId = userId;
        this.travelId = travelId;
        this.puzzleId = puzzleId;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTravelId() {
        return travelId;
    }

    public void setTravelId(String travelId) {
        this.travelId = travelId;
    }

    public String getPuzzleId() {
        return puzzleId;
    }

    public void setPuzzleId(String puzzleId) {
        this.puzzleId = puzzleId;
    }
}
