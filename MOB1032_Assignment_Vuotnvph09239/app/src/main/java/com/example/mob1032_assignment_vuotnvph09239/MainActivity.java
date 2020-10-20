package com.example.mob1032_assignment_thucdvph09264;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mob1032_assignment_thucdvph09264.dao.ClassDAO;
import com.example.mob1032_assignment_thucdvph09264.model.ClassStudent;

public class MainActivity extends AppCompatActivity {
    EditText maLop,tenLop;
    Button xoaTrang, luuLop;
    ClassDAO classDAO;
    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Trang chủ");
        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://ap.poly.edu.vn/");
    }
    public void addClass(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_add_class);
        maLop = (EditText)dialog.findViewById(R.id.edit_maLop);
        tenLop = (EditText)dialog.findViewById(R.id.edit_tenLop);
        xoaTrang = dialog.findViewById(R.id.xoaTrang);
        luuLop = dialog.findViewById(R.id.luuLop);
        xoaTrang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                maLop.setText("");
                tenLop.setText("");
            }
        });
        luuLop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                classDAO = new ClassDAO(MainActivity.this);
                ClassStudent aClass = new ClassStudent(maLop.getText().toString(),tenLop.getText().toString());
                int result = classDAO.insertClass(aClass);
                if (result>0){
                    Toast.makeText(MainActivity.this, "Bạn đã thêm lớp: " + tenLop.getText(),Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
                else {
                    Toast.makeText(MainActivity.this, "Thêm lớp:" + tenLop.getText()+ " đã thất bại",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dialog.show();
    }
    public void viewClass(View view) {
        startActivity(new Intent(this,ClassActivity.class));
    }

    public void qlSinhVien(View view) {
        startActivity(new Intent(this,StudentActivity.class));
    }


}