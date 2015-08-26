package com.example.hover.onebeen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.schema.PuzzleTableSchema;
import com.example.hover.onebeen.db.schema.TravelTableSchema;
import com.example.hover.onebeen.db.schema.UserTableSchema;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "onebeen.db";

    private static final int DATABASE_VERSION = 6;

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(UserTableSchema.CREATE_USER_TABLE);
        database.execSQL(TravelTableSchema.CREATE_TRAVEL_TABLE);

        // puzzle table 새로 생성
        database.execSQL(PuzzleTableSchema.DROP_TABLE_SQL);
        database.execSQL(PuzzleTableSchema.CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
