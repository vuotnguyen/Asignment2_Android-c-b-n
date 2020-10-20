package com.example.mob1032_assignment_thucdvph09264.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.mob1032_assignment_thucdvph09264.model.Student;
import com.example.mob1032_assignment_thucdvph09264.sqlite.SQLite;

import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    Context context;
    private SQLite helper;
    private SQLiteDatabase db;

    public StudentDAO(Context context) {
        this.context = context;
        helper = new SQLite(context);
        db = helper.getWritableDatabase();
    }
    public int insertStudent(Student student){
        ContentValues values = new ContentValues();
        values.put("maSV",student.getMaSV());
        values.put("tenLop", student.getTenLop());
        values.put("tenSV", student.getTenSV());
        values.put("ngaySinh",student.getNgaySinh());
        int result = (int) db.insert("Student",null,values);
        return result;
    }
    public List<Student> getAllStudent(){
        List<Student> students = new ArrayList<>();
        String sql ="SELECT * FROM Student";
        Cursor cursor = db.rawQuery(sql,null);
        int id = 0;
        if (cursor.moveToFirst()){
            do {
                id = id+1;
                Student student = new Student();
                student.setId(id);
                student.setMaSV(cursor.getString(0));
                student.setTenSV(cursor.getString(2));
                student.setTenLop(cursor.getString(1));
                student.setNgaySinh(cursor.getString(3));

                students.add(student);
            }while (cursor.moveToNext());
        }
        return students;
    }
    public int updateStuden(Student student){
        ContentValues values = new ContentValues();
        values.put("tenLop", student.getTenLop());
        values.put("maSV", student.getMaSV());
        values.put("tenSV", student.getTenSV());
        values.put("ngaySinh", student.getNgaySinh());
        int result = db.update("Student",values,"maSV=?", new String[]{student.getMaSV()});
        if (result>0){
            return 1;
        }
        return -1;
    }
    public int deleteStudent(String maSV){
        if (db.delete("Student", "maSV=?",new String[]{maSV})<0){
            return -1;
        }
        return 1;
    }
}
