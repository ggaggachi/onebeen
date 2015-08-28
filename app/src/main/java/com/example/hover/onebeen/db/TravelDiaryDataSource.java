package com.example.hover.onebeen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.hover.onebeen.db.dto.TravelDiary;
import com.example.hover.onebeen.db.dto.TravelStatus;
import com.example.hover.onebeen.db.schema.PuzzleTableSchema;
import com.example.hover.onebeen.db.schema.TravelDiarySchema;
import java.util.ArrayList;

/**
 * Created by Dark on 2015. 8. 27..
 */
public class TravelDiaryDataSource {

    private SQLiteHelper dbHelper;

    public TravelDiaryDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public Long insertTravelDiary(TravelDiary travelDiary) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = getContentValues(travelDiary);

        long travelDiaryId = database.insert(TravelDiarySchema.TABLE_NAME, null, values);
        database.close();

        return travelDiaryId;
    }

    public ArrayList<TravelDiary> getTravelDiaries(String travelStatus) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.query(TravelDiarySchema.TABLE_NAME + "", null, TravelDiarySchema.STATUS_COLUMN + "=?", new String[]{travelStatus}, null, null, null);

        ArrayList<TravelDiary> travelDiaries = new ArrayList<>();

        while (cursor.moveToNext()) {
            TravelDiary travelDiary = new TravelDiary(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), TravelStatus.getValue(cursor.getString(4)), cursor.getString(5));

            travelDiaries.add(travelDiary);
        }

        cursor.close();

        return travelDiaries;
    }

    public TravelDiary getTravelDiary(Long travelDiaryId) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        String[] arg = { String.valueOf(travelDiaryId) };

        Cursor cursor = database.query(TravelDiarySchema.TABLE_NAME, null, TravelDiarySchema.ID_COLUMN + "=?", arg, null, null, null);

        cursor.moveToNext();

        TravelDiary travelDiary = new TravelDiary(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), TravelStatus.getValue(cursor.getString(4)), cursor.getString(5));

        cursor.close();

        return travelDiary;
    }

    public int updateTravelDiary(TravelDiary travelDiary) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String[] args = {String.valueOf(travelDiary.getId())};

        ContentValues values = getContentValues(travelDiary);

        return database.update(TravelDiarySchema.TABLE_NAME, values, TravelDiarySchema.ID_COLUMN + "=?", args);
    }

    @NonNull
    private ContentValues getContentValues(TravelDiary travelDiary) {
        ContentValues values = new ContentValues();
        values.put(TravelDiarySchema.TITLE_COLUMN, travelDiary.getTitle());
        values.put(TravelDiarySchema.START_DATE_COLUMN, travelDiary.getStartDate());
        values.put(TravelDiarySchema.END_DATE_COLUMN, travelDiary.getEndDate());
        values.put(TravelDiarySchema.STATUS_COLUMN, travelDiary.getTravelStatus().getValue());
        values.put(TravelDiarySchema.BACKGROUND_IMAGE_PATH_COLUMN, travelDiary.getBackgroundImagePath());
        return values;
    }
}
