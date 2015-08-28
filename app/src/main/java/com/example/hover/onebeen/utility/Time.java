package com.example.hover.onebeen.utility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Dark on 2015. 8. 28..
 */
public class Time {
    public static String now() {
        Date now = new Date();

        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.DD", Locale.KOREA);

        return format.format(now);
    }
}
