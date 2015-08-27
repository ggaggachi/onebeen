package com.example.hover.onebeen.db.schema;

public class PuzzleTableSchema {
    public static final String TABLE_NAME = "PUZZLE";

    public static final String ID = "_id";
    public static final String TRAVEL_ID = "travel_id";
    public static final String USER_ID = "user_id";
    public static final String STATUS = "status";
    public static final String IMAGE_PATH1 = "puzzle_image_path1";
    public static final String IMAGE_PATH2 = "puzzle_image_path2";
    public static final String IMAGE_PATH3 = "puzzle_image_path3";
    public static final String ORDER = "ordering";
    public static final String PLACE = "place";
    public static final String TODO = "todo";
    public static final String TYPE = "type";
    public static final String DESCRIPTION = "description";

    public static final String DROP_TABLE_SQL = "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME
            + "( " + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + TRAVEL_ID + " INTEGER, "
            + USER_ID + " INTEGER, "
            + STATUS + " TEXT, "
            + IMAGE_PATH1 + " TEXT, "
            + IMAGE_PATH2 + " TEXT, "
            + IMAGE_PATH3 + " TEXT, "
            + ORDER + " INTEGER, "
            + PLACE + " TEXT, "
            + TODO + " TEXT, "
            + TYPE + " TEXT, "
            + DESCRIPTION + " TEXT);";
}
