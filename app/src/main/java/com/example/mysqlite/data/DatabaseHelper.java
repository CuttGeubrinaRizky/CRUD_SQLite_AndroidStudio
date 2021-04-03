package com.example.mysqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_MAHASISWA = "mahasiswaa.db";
    public static final String TABLE_MAHASISWA = "tb_mahasiswa";
    public static final String KEY_NO = "id";
    public static final String KEY_NAMA ="nama";
    public static final String KEY_NPM ="npm";
    public static final String KEY_JURUSAN ="jurusan";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_MAHASISWA, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_MAHASISWA + "(" + KEY_NO + " INTEGER PRIMARY KEY AUTOINCREMENT," + KEY_NAMA + " TEXT," + KEY_NPM + " TEXT," + KEY_JURUSAN + " TEXT" + ")";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MAHASISWA);
        onCreate(db);
    }

    public boolean addOne(String nama, String npm, String jurusan) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_NAMA, nama);
        cv.put(KEY_NPM, npm);
        cv.put(KEY_JURUSAN, jurusan);

        long insert = db.insert(TABLE_MAHASISWA, null, cv);
        if (insert == -1){
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_MAHASISWA,null);
        return res;
    }

    public boolean updateData(String id, String nama, String npm, String jurusan) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_NO, id);
        cv.put(KEY_NAMA, nama);
        cv.put(KEY_NPM, npm);
        cv.put(KEY_JURUSAN, jurusan);
        db.update(TABLE_MAHASISWA, cv, "id = ?", new String[] { id });
        db.close();
        return true;
    }

    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_MAHASISWA, "KEY_NO = ?", new String[] { id });
    }
}
