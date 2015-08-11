package com.example.hover.onebeen.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hover.onebeen.db.dto.Puzzle;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.db.schema.PuzzleTableSchema;
import com.example.hover.onebeen.db.schema.UserTableSchema;

import java.util.Objects;

public class PuzzleDataSource {
	private SQLiteHelper dbHelper;

	public PuzzleDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void insertPuzzle(Puzzle puzzle) {
		SQLiteDatabase database = dbHelper.getWritableDatabase();

		Object[] args = {puzzle.getType(), puzzle.getTitle(), puzzle.getDescription(), puzzle.getMediaUri()};
		try {
			database.execSQL("INSERT INTO " + PuzzleTableSchema.PUZZLE_TABLE
				+ "(" + PuzzleTableSchema.PUZZLE_TYPE_COLUMN
				+ ", " + PuzzleTableSchema.PUZZLE_TITLE_COLUMN
				+ ", " + PuzzleTableSchema.PUZZLE_DESCRIPTION_COLUMN
				+ ", " + PuzzleTableSchema.PUZZLE_MEDIA_COLUMN
				+ ") values(?, ?, ?, ?);", args);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			database.close();
		}
	}

	public Puzzle getPuzzleById(long id) {
		SQLiteDatabase database = dbHelper.getReadableDatabase();
		String[] arg = {String.valueOf(id)};

		Cursor cursor = database.rawQuery(
			"select * from " + PuzzleTableSchema.PUZZLE_TABLE + " where " + PuzzleTableSchema.PUZZLE_ID_COLUMN + " is ?", arg);

		Puzzle puzzle = null;
		try {
			cursor.moveToFirst();
			puzzle = new Puzzle(cursor.getLong(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			database.close();
		}

		return puzzle;
	}
}
