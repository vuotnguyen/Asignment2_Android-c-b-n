package com.example.mob1032_assignment_thucdvph09264.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob1032_assignment_thucdvph09264.model.ClassStudent;
import com.example.mob1032_assignment_thucdvph09264.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;

public class ClassDAO {
    private Context context;
    private SQLite helper;
    private SQLiteDatabase db;

    public ClassDAO(Context context) {
        this.context = context;
        helper = new SQLite(context);
        db = helper.getWritableDatabase();
    }
    public int insertClass(ClassStudent aClass){
        ContentValues values = new ContentValues();
        values.put("maLop",aClass.getMaLop());
        values.put("tenLop",aClass.getTenLop());
        int result = (int) db.insert("Class", null, values);
        return result;
    }
    public List<ClassStudent> getAllClass(){
        List<ClassStudent> classes = new ArrayList<>();
        String sql = "SELECT * FROM Class";
        Cursor cursor = db.rawQuery(sql, null);
        int id = 0;
        if (cursor.moveToFirst()){
            do {
                id = id+1;
                ClassStudent aClass = new ClassStudent();
                aClass.setId(id);
                aClass.setMaLop(cursor.getString(0));
                aClass.setTenLop(cursor.getString(1));
                classes.add(aClass);
            }while (cursor.moveToNext());
        }
        return classes;
    }
    public List<ClassStudent> getClassStuden(){
        List<ClassStudent> classStudents = new ArrayList<>();
        String sql = "SELECT * FROM Class";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            do {
                ClassStudent classStudent = new ClassStudent();
                classStudent.setMaLop(cursor.getString(0));
                classStudent.setTenLop(cursor.getString(1));
                classStudents.add(classStudent);
            }while (cursor.moveToNext());
        }
        return classStudents;
    }
    public int deleteClass(String maLop){
        if (db.delete("Class","maLop=?",new String[]{maLop})<0){
            return -1;
        }
        return 1;
    }
    public int updateClass(ClassStudent classStudent){
        ContentValues values = new ContentValues();
        values.put("maLop",classStudent.getMaLop());
        values.put("tenLop",classStudent.getTenLop());
        int result = db.update("Class",values,"maLop=?",new String[]{classStudent.getMaLop()});
        if (result>0) {
            return 1;
        }
        return -1;
    }
}
