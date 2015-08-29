package com.example.hover.onebeen.db.dto;

import android.os.Parcel;
import android.os.Parcelable;

public class Puzzle implements Parcelable{
    private long id;
    private String travelDiaryId;
    private String userId;
    private String status;
    private String imagePath1;
    private String imagePath2;
    private String imagePath3;
    private int ordering;
    private String place;
    private String todo;
    private String type;

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public Puzzle() {
    }

    public Puzzle(long id, String travelDiaryId, String userId, String status, String imagePath1, String imagePath2, String imagePath3, int ordering, String place, String todo, String type, String description) {
        this.id = id;
        this.travelDiaryId = travelDiaryId;
        this.userId = userId;
        this.status = status;
        this.imagePath1 = imagePath1;
        this.imagePath2 = imagePath2;
        this.imagePath3 = imagePath3;
        this.ordering = ordering;
        this.place = place;
        this.todo = todo;
        this.type = type;
        this.description = description;
    }

    protected Puzzle(Parcel in) {
        id = in.readLong();
        userId = in.readString();
        status = in.readString();
        imagePath1 = in.readString();
        imagePath2 = in.readString();
        imagePath3 = in.readString();
        ordering = in.readInt();
        place = in.readString();
        todo = in.readString();
        type = in.readString();
    }

    public static final Creator<Puzzle> CREATOR = new Creator<Puzzle>() {
        @Override
        public Puzzle createFromParcel(Parcel in) {
            return new Puzzle(in);
        }

        @Override
        public Puzzle[] newArray(int size) {
            return new Puzzle[size];
        }
    };

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagePath1() {
        return imagePath1;
    }

    public void setImagePath1(String imagePath1) {
        this.imagePath1 = imagePath1;
    }

    public String getImagePath2() {
        return imagePath2;
    }

    public void setImagePath2(String imagePath2) {
        this.imagePath2 = imagePath2;
    }

    public String getImagePath3() {
        return imagePath3;
    }

    public void setImagePath3(String imagePath3) {
        this.imagePath3 = imagePath3;
    }

    public int getOrdering() {
        return ordering;
    }

    public void setOrdering(int ordering) {
        this.ordering = ordering;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeLong(id);
        dest.writeString(userId);
        dest.writeString(status);
        dest.writeString(imagePath1);
        dest.writeString(imagePath2);
        dest.writeString(imagePath3);
        dest.writeInt(ordering);
        dest.writeString(place);
        dest.writeString(todo);
        dest.writeString(type);
    }

    public String getTravelDiaryId() {
        return travelDiaryId;
    }

    public void setTravelDiaryId(String travelDiaryId) {
        this.travelDiaryId = travelDiaryId;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Puzzle{" +
                "id=" + id +
                ", travelDiaryId='" + travelDiaryId + '\'' +
                ", userId='" + userId + '\'' +
                ", status='" + status + '\'' +
                ", imagePath1='" + imagePath1 + '\'' +
                ", imagePath2='" + imagePath2 + '\'' +
                ", imagePath3='" + imagePath3 + '\'' +
                ", ordering=" + ordering +
                ", place='" + place + '\'' +
                ", todo='" + todo + '\'' +
                ", type='" + type + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getImagePathCount() {
        int imagePathCount = 0;

        if (!(imagePath1 == null || imagePath1.equals(""))) {
            imagePathCount++;
        }

        if(!(imagePath2 == null || imagePath2.equals(""))) {
            imagePathCount++;
        }

        if(!(imagePath3 == null || imagePath3.equals(""))) {
            imagePathCount++;
        }

        return imagePathCount;
    }
}
