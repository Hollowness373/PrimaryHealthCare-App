package com.example.phc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class HistoryDatabaseHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "History.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "history";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_TITLE = "TITLE";

    public HistoryDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_TITLE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    void insertOnHistory(String title){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put(COLUMN_TITLE, title);
        long result = db.insert(TABLE_NAME, null, contentValues1);
        if (result == -1){
            Toast.makeText(context, "History went wrong.", Toast.LENGTH_SHORT).show();
        } else {

        }
    }

    Cursor readOnHistory(){
        String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY ID DESC";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
