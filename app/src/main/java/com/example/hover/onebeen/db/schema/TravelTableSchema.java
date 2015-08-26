package com.example.hover.onebeen.db.schema;

public class TravelTableSchema {
    public static final String TABLE_NAME = "TRAVEL";

    public static final String TRAVEL_ID_COLUMN = "_id";
    public static final String TRAVEL_USER_ID_COLUMN = "user_id";
    public static final String TRAVEL_TITLE = "title";

    public static final String CREATE_TRAVEL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "( " + TRAVEL_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRAVEL_USER_ID_COLUMN + " TEXT, "
            + TRAVEL_TITLE + " TEXT);";

    public static final String INSERT_TRAVEL_TABLE = "INSERT INTO " + TravelTableSchema.TABLE_NAME +
            "(" + TravelTableSchema.TRAVEL_USER_ID_COLUMN + "," +
            " " + TravelTableSchema.TRAVEL_TITLE + ") values(?, ?, ?);";
}
