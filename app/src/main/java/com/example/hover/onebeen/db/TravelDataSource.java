package com.example.hover.onebeen.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.Travel;
import com.example.hover.onebeen.db.dto.TravelRequestParam;
import com.example.hover.onebeen.db.schema.TravelTableSchema;

import java.util.ArrayList;

public class TravelDataSource {
    private SQLiteHelper dbHelper;

    public TravelDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void insertTravel(TravelRequestParam travel) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String[] args = {travel.getUserId(), travel.getTravelId(), travel.getPuzzleId()};

        try {
            database.execSQL("INSERT INTO " + TravelTableSchema.TRAVEL_TABLE +
                    "(" + TravelTableSchema.TRAVEL_USER_ID_COLUMN + "," +
                    " " + TravelTableSchema.TRAVEL_TRAVEL_ID_COLUMN + "," +
                    " " + TravelTableSchema.TRAVEL_PUZZLE_ID_COLUMN +
                    ") values(?, ?, ?);", args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }

//    public Travel getTravel(String travelId) {
//        SQLiteDatabase database = dbHelper.getReadableDatabase();
//
//        String[] args = {travelId};
//
//        Cursor cursor = database.rawQuery("select * from " + TravelTableSchema.TRAVEL_TABLE + " where travelId = ?", args);
//
//        Travel travel = null;
//
//        try {
//            cursor.moveToFirst();
//
//            travel = new Travel(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            database.close();
//        }
//
//        return travel;
//    }
    public Travel getTravel(String userId, String travelId, String puzzleId) {
        ArrayList<Puzzle> puzzles = new ArrayList<>();
        puzzles.add(puzzle());

        return new Travel(1L, "ekdxhrl", "1", puzzles);
    }

    private Puzzle puzzle() {
        Puzzle puzzle = new Puzzle();

        puzzle.setId(1L);
        puzzle.setDescription("Description");
        puzzle.setMediaUri("URL");
        puzzle.setTitle("Title");
        puzzle.setType("Type");
        return puzzle;
    }
}
