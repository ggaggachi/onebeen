package com.example.hover.onebeen.utility;

import android.content.Context;

public class CircleRatio {
    public static int getWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int marginLeft(Context context) {
        return getWidth(context) / 2;
    }

    public static int marginTop(Context context) {
        return getHeight(context) / 2;
    }

    public static int marginRight(Context context) {
        return 0;
    }

    public static int marginBottom(Context context) {
        return 0;
    }
}
