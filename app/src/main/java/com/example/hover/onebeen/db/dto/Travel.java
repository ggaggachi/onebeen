package com.example.hover.onebeen.db.dto;

import java.util.List;

public class Travel {
    private Long _id;
    private String userId;
    private String travelId;
    private List<Puzzle> puzzles;

    public Travel(Long _id, String userId, String travelId, List<Puzzle> puzzles) {
        this._id = _id;
        this.userId = userId;
        this.travelId = travelId;
        this.puzzles = puzzles;
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

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public List<Puzzle> getPuzzles() {
        return puzzles;
    }

    public void setPuzzles(List<Puzzle> puzzles) {
        this.puzzles = puzzles;
    }

    @Override
    public String toString() {
        return "Travel{" +
                "_id=" + _id +
                ", userId='" + userId + '\'' +
                ", travelId='" + travelId + '\'' +
                ", puzzles=" + puzzles +
                '}';
    }
}
