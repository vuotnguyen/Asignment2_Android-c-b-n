package com.example.mob1032_assignment_thucdvph09264.model;

import java.util.Date;

public class Student {
    private int id;
    private String maSV, tenSV, tenLop;
    private String ngaySinh;

    public Student(String maSV, String tenSV, String ngaySinh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.ngaySinh = ngaySinh;
    }
    public Student(String tenLop, String maSV, String tenSV, String ngaySinh) {
        this.maSV = maSV;
        this.tenSV = tenSV;
        this.ngaySinh = ngaySinh;
        this.tenLop = tenLop;
    }
    public Student() {

    }

    public int getId() {
        return id;
    }

    public String getTenLop() {
        return tenLop;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSV() {
        return maSV;
    }

    public void setMaSV(String maSV) {
        this.maSV = maSV;
    }

    public String getTenSV() {
        return tenSV;
    }

    public void setTenSV(String tenSV) {
        this.tenSV = tenSV;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }
}
