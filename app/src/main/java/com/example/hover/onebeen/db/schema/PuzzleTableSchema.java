package com.example.hover.onebeen.db.schema;

public class PuzzleTableSchema {
	public static final String PUZZLE_TABLE = "PUZZLE";

	public static final String PUZZLE_ID_COLUMN = "puzzle_id";
	public static final String PUZZLE_TYPE_COLUMN = "puzzle_type";
	public static final String PUZZLE_TITLE_COLUMN = "puzzle_title";
	public static final String PUZZLE_DESCRIPTION_COLUMN = "puzzle_description";
	public static final String PUZZLE_MEDIA_COLUMN = "puzzle_media";

	public static final String CREATE_PUZZLE_TABLE = "CREATE TABLE IF NOT EXISTS " + PUZZLE_TABLE
		+ "( " + PUZZLE_ID_COLUMN + " INTEGER PRIMARY KEY AUTOINCREMENT, "
		+ PUZZLE_TYPE_COLUMN + " TEXT, "
		+ PUZZLE_TITLE_COLUMN + " TEXT, "
		+ PUZZLE_DESCRIPTION_COLUMN + " TEXT, "
		+ PUZZLE_MEDIA_COLUMN + " TEXT);";
}
