package com.example.hover.onebeen.db.schema;

/**
 * Created by Dark on 2015. 8. 27..
 */
public class TravelDiarySchema {
    public static final String TABLE_NAME = "TRAVEL_DIARY";

    public static final String ID_COLUMN = "_id";
    public static final String TITLE_COLUMN = "title";
    public static final String START_DATE_COLUMN = "start_date";
    public static final String END_DATE_COLUMN = "end_date";
    public static final String STATUS_COLUMN = "status";
    public static final String BACKGROUND_IMAGE_PATH_COLUMN = "background_image_path";

    public static final String CREATE_TRAVEL_DIARY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "( " + ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TITLE_COLUMN + " TEXT, "
            + START_DATE_COLUMN + " TEXT, "
            + END_DATE_COLUMN + " TEXT, "
            + STATUS_COLUMN + " TEXT, "
            + BACKGROUND_IMAGE_PATH_COLUMN + " TEXT);";

    public static final String INSERT_TRAVEL_DIARY_TABLE = "INSERT INTO " + TABLE_NAME +
            "(" + TITLE_COLUMN + "," +
            "(" + START_DATE_COLUMN + "," +
            "(" + END_DATE_COLUMN + "," +
            "(" + STATUS_COLUMN + "," +
            "(" + BACKGROUND_IMAGE_PATH_COLUMN + ") values(?, ?, ?, ?, ?);";
}
