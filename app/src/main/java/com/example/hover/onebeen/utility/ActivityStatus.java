package com.example.hover.onebeen.utility;

/**
 * Created by Dark on 2015. 8. 27..
 */
public enum ActivityStatus {
    MAKE_DIARY(1),
    MAIN_ACTIVITY(2);

    private int activityStatus;

    ActivityStatus(int activityStatus) {
        this.activityStatus = activityStatus;
    }

    public int getValue() {
        return this.activityStatus;
    }
}
