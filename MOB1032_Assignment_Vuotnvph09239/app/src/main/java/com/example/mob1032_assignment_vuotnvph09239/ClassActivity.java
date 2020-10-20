package com.example.mob1032_assignment_thucdvph09264;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mob1032_assignment_thucdvph09264.adapter.ClassAdapter;
import com.example.mob1032_assignment_thucdvph09264.dao.ClassDAO;
import com.example.mob1032_assignment_thucdvph09264.model.ClassStudent;

import java.util.List;

public class ClassActivity extends AppCompatActivity {
    ListView lvClass;
    List<ClassStudent> classList;
    ClassDAO classDAO;
    ClassAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class);
        setTitle("Danh sách lớp");
        lvClass = findViewById(R.id.lvClass);
        classDAO = new ClassDAO(this);
        classList = classDAO.getAllClass();
        adapter = new ClassAdapter(ClassActivity.this, R.layout.item_class, classList);
        lvClass.setAdapter(adapter);

        lvClass.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                xoaClass(position);
                return true;
            }
        });
        lvClass.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                editClass(position);
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void editClass(final int position){
        final EditText maLop,tenLop;
        Button edit, cancel;
        //Khởi tạo dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_edit_classs);
        //ánh xạ
        maLop = dialog.findViewById(R.id.edit_update_maLop);
        tenLop = dialog.findViewById(R.id.edit_update_tenLop);
        edit = dialog.findViewById(R.id.btn_edit);
        cancel = dialog.findViewById(R.id.btn_cancel);
        //show ma lop & ten lop len dialog
        tenLop.setText(classList.get(position).getTenLop());
        maLop.setText(classList.get(position).getMaLop());
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String _tenLop = tenLop.getText().toString();
                final String _maLop = classList.get(position).getMaLop();
                final ClassStudent classStudent = new ClassStudent(_maLop,_tenLop);
                if (!_tenLop.isEmpty()){
                    if (classDAO.updateClass(classStudent)>0){
                        Toast.makeText(ClassActivity.this,"Bạn đã sửa thành công",Toast.LENGTH_SHORT).show();
                        adapter.notifyDataSetChanged();
                        dialog.cancel();
                    }else {
                        Toast.makeText(ClassActivity.this,"Thất bại",Toast.LENGTH_SHORT).show();
                    }
                }
                else {
                    Toast.makeText(ClassActivity.this,"Hãy nhập tên Class mới",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
    private void xoaClass(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(ClassActivity.this);
        builder.setTitle("Xác nhận");
        builder.setMessage("Bạn có muốn xóa lớp: "+classList.get(position).getTenLop()+" ?");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String maLop = classList.get(position).getMaLop();
                String tenLop = classList.get(position).getTenLop();
                int result = classDAO.deleteClass(maLop);
                if (result == 1){
                    classList.remove(position);
                    adapter.notifyDataSetChanged();
                    Toast.makeText(ClassActivity.this, "Đã xóa: "+tenLop , Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ClassActivity.this, "Không thể xóa: "+tenLop , Toast.LENGTH_SHORT).show();
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
}