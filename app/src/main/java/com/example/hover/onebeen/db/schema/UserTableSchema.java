package com.example.hover.onebeen.db.schema;

public class UserTableSchema {
    public static final String USER_TABLE = "USER";

    public static final String USER_ID_COLUMN = "user_id";
    public static final String NAME_COLUMN = "user_name";

    public static final String CREATE_USER_TABLE = "CREATE TABLE IF NOT EXISTS " + USER_TABLE
            + "( " + USER_ID_COLUMN + " TEXT PRIMARY KEY, "
            + NAME_COLUMN + " TEXT);";
}
