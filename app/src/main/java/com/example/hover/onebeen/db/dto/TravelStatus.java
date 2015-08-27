package com.example.hover.onebeen.db.dto;

/**
 * Created by Dark on 2015. 8. 27..
 */
public enum TravelStatus {
    PLANNING("1"),
    ONGOING("2"),
    BEEN("3");

    private String value;

    TravelStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    public static TravelStatus getValue(String travelStatus) {
        switch(travelStatus){
            case "1":
                return TravelStatus.PLANNING;
            case "2":
                return TravelStatus.ONGOING;
            case "3":
                return TravelStatus.BEEN;
        }

        throw new IllegalArgumentException("TravelStatus에 정의되지 않은 파라미터입니다.");
    }
}
