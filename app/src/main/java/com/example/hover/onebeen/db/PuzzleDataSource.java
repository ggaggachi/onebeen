package com.example.hover.onebeen.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.schema.PuzzleTableSchema;

import java.util.ArrayList;
import java.util.List;

public class PuzzleDataSource {

    private SQLiteHelper dbHelper;

    public PuzzleDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public long addPuzzle(Puzzle puzzle) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        ContentValues values = getPuzzleContentValues(puzzle);

        long insert = database.insert(PuzzleTableSchema.TABLE_NAME, null, values);
        database.close();

        return insert;
    }

    public Puzzle getPuzzle(long id) {
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        String[] arg = {String.valueOf(id)};
        Cursor cursor = database.query(PuzzleTableSchema.TABLE_NAME, null, PuzzleTableSchema.ID + "=?", arg, null, null, null);

        if (!cursor.moveToNext()) {
            cursor.close();
            return new Puzzle();
        }

        Puzzle puzzle = new Puzzle(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8),
                cursor.getString(9), cursor.getString(10), cursor.getString(11));
        return puzzle;
    }

    public int updatePuzzle(Puzzle puzzle) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] arg = {String.valueOf(puzzle.getId())};

        ContentValues values = getPuzzleContentValues(puzzle);

        return database.update(PuzzleTableSchema.TABLE_NAME, values, PuzzleTableSchema.ID + "=?", arg);
    }

    public void deletePuzzle(Puzzle puzzle) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();
        String[] arg = {String.valueOf(puzzle.getId())};

        database.delete(PuzzleTableSchema.TABLE_NAME, PuzzleTableSchema.ID + "=?", arg);
        database.close();
    }

    @NonNull
    private ContentValues getPuzzleContentValues(Puzzle puzzle) {
        ContentValues values = new ContentValues();
        values.put(PuzzleTableSchema.USER_ID, puzzle.getUserId());
        values.put(PuzzleTableSchema.STATUS, puzzle.getStatus());
        values.put(PuzzleTableSchema.TITLE, puzzle.getTitle());
        values.put(PuzzleTableSchema.DESCRIPTION, puzzle.getDescription());
        values.put(PuzzleTableSchema.IMAGE_PATH1, puzzle.getImagePath1());
        values.put(PuzzleTableSchema.IMAGE_PATH2, puzzle.getImagePath2());
        values.put(PuzzleTableSchema.IMAGE_PATH3, puzzle.getImagePath3());
        values.put(PuzzleTableSchema.ORDER, puzzle.getOrdering());
        values.put(PuzzleTableSchema.PLACE, puzzle.getPlace());
        values.put(PuzzleTableSchema.TODO, puzzle.getTodo());
        values.put(PuzzleTableSchema.TYPE, puzzle.getType());
        return values;
    }

    public List<Puzzle> getPuzzles(Long travelId) {
        String[] arg = {String.valueOf(travelId)};

        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = database.query(PuzzleTableSchema.TABLE_NAME, null, PuzzleTableSchema.TRAVEL_ID + "=?", arg, null, null, null);

        List<Puzzle> puzzles = new ArrayList<>();

        while (cursor.moveToNext()) {
            Puzzle puzzle = new Puzzle(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3),
                    cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getString(7), cursor.getInt(8),
                    cursor.getString(9), cursor.getString(10), cursor.getString(11));

            puzzles.add(puzzle);
        }

        cursor.close();

        return puzzles;
    }
}
