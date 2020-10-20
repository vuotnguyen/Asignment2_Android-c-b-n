package com.example.mob1032_assignment_thucdvph09264.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mob1032_assignment_thucdvph09264.R;
import com.example.mob1032_assignment_thucdvph09264.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter {
    Context context;
    int resource;
    List<Student> students;
    LayoutInflater inflater;

    public StudentAdapter(Context context, int resource,List<Student> students) {
        super(context, resource, students);
        this.context = context;
        this.resource = resource;
        this.students = students;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position,View convertView,ViewGroup parent) {
            ViewHolder viewHolder = new ViewHolder();
            if (convertView == null){
                convertView = inflater.inflate(R.layout.item_student, null);
                viewHolder.tvID = (TextView) convertView.findViewById(R.id.tv_id_sv);
                viewHolder.tvTenSV = (TextView) convertView.findViewById(R.id.tv_ten_sv);
                viewHolder.tvNgaySinh = (TextView) convertView.findViewById(R.id.tvNgaySinh);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            Student student = students.get(position);
            viewHolder.tvID.setText(String.valueOf(student.getId()));
            viewHolder.tvTenSV.setText(student.getTenSV());
            viewHolder.tvNgaySinh.setText(student.getNgaySinh());
        return convertView;
    }
    public class ViewHolder{
        TextView tvID, tvTenSV, tvNgaySinh;
    }
}
