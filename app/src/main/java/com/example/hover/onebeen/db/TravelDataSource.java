package com.example.hover.onebeen.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.Travel;
import com.example.hover.onebeen.db.dto.TravelRequestParam;
import com.example.hover.onebeen.db.schema.PuzzleTableSchema;
import com.example.hover.onebeen.db.schema.TravelTableSchema;

import java.util.ArrayList;

public class TravelDataSource {
    private SQLiteHelper dbHelper;

    public TravelDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void insertTravel(TravelRequestParam travel) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String[] args = {travel.getUserId(), travel.getTitle()};

        try {
            database.execSQL(TravelTableSchema.INSERT_TRAVEL_TABLE, args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }

    public void deletePuzzleInTravel(String travelId) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] args = {travelId};

        database.rawQuery("delete from " + TravelTableSchema.TABLE_NAME + " where " + TravelTableSchema.TRAVEL_ID_COLUMN + " = ?", args);
    }
}