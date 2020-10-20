package com.example.mob1032_assignment_thucdvph09264.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLite extends SQLiteOpenHelper {
    private Context context;
    private static final String NAME_DATA = "QLSV";
    private static final int version = 3;
    public SQLite(Context context) {
        super(context, NAME_DATA, null, version);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql_Class = "CREATE TABLE Class( maLop TEXT PRIMARY KEY, tenLop TEXT)";
        String sql_Student = "CREATE TABLE Student( maSV TEXT PRIMARY KEY, tenLop TEXT, tenSV TEXT, ngaySinh DATE)";
        db.execSQL(sql_Class);
        db.execSQL(sql_Student);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Class");
        db.execSQL("DROP TABLE IF EXISTS Student");
        onCreate(db);
    }
}
