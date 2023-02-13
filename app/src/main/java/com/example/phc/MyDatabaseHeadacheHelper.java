package com.example.phc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHeadacheHelper extends SQLiteOpenHelper {

    Context context;
    private static final String DATABASE_NAME = "Headache.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "headachereviews";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_RATING = "Rating";
    private static final String COLUMN_COMMENT = "COMMENT";

    public MyDatabaseHeadacheHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_RATING + " TEXT, " +
                COLUMN_COMMENT + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertCommentHeadache(String username, float rating, String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COLUMN_USERNAME, username);
        contentValues1.put(COLUMN_RATING, rating);
        contentValues1.put(COLUMN_COMMENT, comment);
        long result = db.insert(TABLE_NAME, null, contentValues1);
        if (result == -1){
            Toast.makeText(context, "Something went wrong.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Comment Added!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllDataHeadache(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
