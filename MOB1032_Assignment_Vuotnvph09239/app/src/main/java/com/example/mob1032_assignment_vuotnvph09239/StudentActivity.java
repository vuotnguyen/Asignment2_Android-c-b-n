package com.example.mob1032_assignment_thucdvph09264;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mob1032_assignment_thucdvph09264.adapter.StudentAdapter;
import com.example.mob1032_assignment_thucdvph09264.dao.ClassDAO;
import com.example.mob1032_assignment_thucdvph09264.dao.StudentDAO;
import com.example.mob1032_assignment_thucdvph09264.model.ClassStudent;
import com.example.mob1032_assignment_thucdvph09264.model.Student;

import java.util.List;

public class StudentActivity extends AppCompatActivity {
    ListView lv_Student;
    Spinner spinner;
    EditText maSV, tenSV, ngaySinh;

    List<ClassStudent> classStudents;
    ClassDAO classDAO;

    List<Student> students;
    StudentDAO studentDAO;
    StudentAdapter studentAdapter;
    String arr[] = {"Lập trình mobile", "Digital maketting","Quản trị kinh doanh"};
    String tenLop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        lv_Student = findViewById(R.id.list_student);
        spinner = findViewById(R.id.spnTenLop);
        maSV = findViewById(R.id.edit_maSV);
        tenSV = findViewById(R.id.edit_tenSV);
        ngaySinh = findViewById(R.id.edit_ngaySinh);
        //show spinner
        classDAO = new ClassDAO(this);
        classStudents = classDAO.getClassStuden();

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arr);
        spinner.setAdapter(adapter);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // show list sinh viên
        studentDAO = new StudentDAO(this);
        students = studentDAO.getAllStudent();
        studentAdapter =new StudentAdapter(this,R.layout.item_student,students);
        lv_Student.setAdapter(studentAdapter);
        lv_Student.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                updateStudent(position);
                Toast.makeText(getApplicationContext(),""+students.get(position).getMaSV(),Toast.LENGTH_SHORT).show();
            }
        });
        lv_Student.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                deleteStudent(position);
                return true;
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tenLop = arr[position];
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                 tenLop =arr[0];
            }
        });
    }
    public void updateStudent(final int position){
        TextView tenLop;
        final EditText _maSV, _tenSV, _ngaySinh;
        Button update, cancel;
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_student);
        dialog.setTitle("Sửa sinh viên");
        tenLop = dialog.findViewById(R.id.tv_edit_student_mon);
        _maSV = dialog.findViewById(R.id.edit_update_maSV);
        _tenSV = dialog.findViewById(R.id.edit_update_tenSV);
        _ngaySinh = dialog.findViewById(R.id.edit_update_ngaySinh);
        update = dialog.findViewById(R.id.btn_update_student);
        cancel = dialog.findViewById(R.id.btn_cancel_update_student);
        //showw
        tenLop.setText(students.get(position).getTenLop());
        _maSV.setText(students.get(position).getMaSV());
        _tenSV.setText(students.get(position).getTenSV());
        _ngaySinh.setText(students.get(position).getNgaySinh());

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student(_maSV.getText().toString(),_tenSV.getText().toString(),_ngaySinh.getText().toString());
                if (studentDAO.updateStuden(student)>0){
                    Toast.makeText(getApplicationContext(),"update thành công", Toast.LENGTH_SHORT).show();
                    students.remove(position);
                    studentAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(getApplicationContext(),"Thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void deleteStudent(final int position){
        final String _maSV = students.get(position).getMaSV();
        final String _tenSV= students.get(position).getTenSV();
        AlertDialog.Builder builder = new AlertDialog.Builder(StudentActivity.this);
        builder.setTitle("Xác Nhận");
        builder.setMessage("Bạn có muốn xóa sinh viên: "+_tenSV+" ?");
        builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (studentDAO.deleteStudent(_maSV)>0){
                    students.remove(position);
                    studentAdapter.notifyDataSetChanged();
                    Toast.makeText(StudentActivity.this,"Đã xóa sinh viên: "+ _tenSV, Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(StudentActivity.this,"Lỗi xóa sinh viên: "+ _tenSV, Toast.LENGTH_SHORT).show();
                }
            }
        });
        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }
    public void addSV(View view) {
        String _tenLop = tenLop;
        String _maSV = maSV.getText().toString();
        String _tenSV= tenSV.getText().toString();
        String _ngaySinh = ngaySinh.getText().toString();
        if (!_tenLop.isEmpty() && !_maSV.isEmpty() && !_tenSV.isEmpty() && !_ngaySinh.isEmpty()){
            StudentDAO studentDAO = new StudentDAO(this);
            Student student = new Student(_tenLop,_maSV,_tenSV,_ngaySinh);
            int result = studentDAO.insertStudent(student);
            if (result > 0){
                studentAdapter.notifyDataSetChanged();
                Toast.makeText(this,"Đã thêm thành công sinh viên: ( "+ _tenSV+ " )", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this,"Thêm sinh viên: ( "+ _tenSV+" ) thất bại", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this,"Hãy kiểm tra lại và nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}