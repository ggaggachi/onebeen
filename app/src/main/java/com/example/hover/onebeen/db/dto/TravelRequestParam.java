package com.example.hover.onebeen.db.dto;

public class TravelRequestParam {
    private Integer _id;
    private String userId;
    private String title;

    public TravelRequestParam(Integer _id, String userId, String title) {
        this._id = _id;
        this.userId = userId;
        this.title = title;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
