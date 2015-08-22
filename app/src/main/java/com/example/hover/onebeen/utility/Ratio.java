package com.example.hover.onebeen.utility;

import android.content.Context;

public class Ratio {
    public static final int STANDARD_HEIGHT_RATIO = 2;

    public static int getHeight(Context context) {
        int height = context.getResources().getDisplayMetrics().heightPixels;

        if (height != 0) {
            return height / 10;
        } else {
            return height;
        }
    }

    public static int getWidth(Context context) {
        int width = context.getResources().getDisplayMetrics().widthPixels;

        if (width != 0) {
            return width / 10;
        } else {
            return width;
        }
    }
}
