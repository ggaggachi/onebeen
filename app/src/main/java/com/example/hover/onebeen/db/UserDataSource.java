package com.example.hover.onebeen.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.hover.onebeen.db.dto.User;
import com.example.hover.onebeen.db.schema.UserTableSchema;

public class UserDataSource {
    private SQLiteHelper dbHelper;

    public UserDataSource(Context context) {
        dbHelper = new SQLiteHelper(context);
    }

    public void insertUser(User user) {
        SQLiteDatabase database = dbHelper.getWritableDatabase();

        String[] args = {user.getId(), user.getName()};
        try {
            database.execSQL("INSERT INTO USER(user_id, user_name) values(?, ?);", args);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }
    }

    public User getUser() {
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        Cursor cursor = database.rawQuery("select * from " + UserTableSchema.USER_TABLE, null);

        User user = null;

        try {
            cursor.moveToFirst();

            user = new User(cursor.getString(0), cursor.getString(1));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            database.close();
        }

        // Default User Guest Data
        if(user == null) {
            user = new User("1234", "Guest");
        }

        return user;
    }
}

