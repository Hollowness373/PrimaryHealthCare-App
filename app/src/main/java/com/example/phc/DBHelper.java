package com.example.phc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private Context context;
    private static final String DBNAME = "Login.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_EMAIL = "Email";
    private static final String COLUMN_PASSWORD = "Password";

    public DBHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_USERNAME + " TEXT, " +
                COLUMN_EMAIL + " TEXT, " +
                COLUMN_PASSWORD + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(String username, String email, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_USERNAME, username);
        contentValues.put(COLUMN_EMAIL, email);
        contentValues.put(COLUMN_PASSWORD, password);
        long result = MyDB.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Boolean checkUsername(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{username});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Boolean checkUsernamePassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?", new String[]{username,password});
        if (cursor.getCount() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Cursor readUsername(String user){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USERNAME + " = ?", new String[]{user});
        return cursor;
    }
    void updateData(String row, String username, String email, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_USERNAME, username);
        cv.put(COLUMN_EMAIL, email);
        cv.put(COLUMN_PASSWORD, password);

        long result = db.update(TABLE_NAME, cv, "ID=?", new String[]{row});
        if (result == -1){
            Toast.makeText(context, "Update Failed.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successfully Updated!", Toast.LENGTH_SHORT).show();
        }

    }
}
