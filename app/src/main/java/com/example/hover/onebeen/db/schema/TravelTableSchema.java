package com.example.hover.onebeen.db.schema;

public class TravelTableSchema {
    public static final String TRAVEL_TABLE = "TRAVEL";

    public static final String TRAVEL_ID_COLUMN = "_id";
    public static final String TRAVEL_USER_ID_COLUMN = "user_id";
    public static final String TRAVEL_TRAVEL_ID_COLUMN = "travel_id";
    public static final String TRAVEL_PUZZLE_ID_COLUMN = "puzzle_id";

    public static final String CREATE_TRAVEL_TABLE = "CREATE TABLE IF NOT EXISTS " + TRAVEL_TABLE
            + "( _id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRAVEL_USER_ID_COLUMN + " TEXT, "
            + TRAVEL_TRAVEL_ID_COLUMN + " TEXT, "
            + TRAVEL_PUZZLE_ID_COLUMN + " TEXT);";
}
