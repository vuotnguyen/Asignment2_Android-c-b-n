package com.example.mob1032_assignment_thucdvph09264.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mob1032_assignment_thucdvph09264.R;
import com.example.mob1032_assignment_thucdvph09264.model.ClassStudent;

import java.util.List;

public class ClassAdapter extends ArrayAdapter {
     Context context;
     int resurce;
     List<ClassStudent> classList;
     LayoutInflater inflater;

    public ClassAdapter(Context context, int resource, List<ClassStudent> classList) {
        super(context, resource, classList);
        this.context = context;
        this.resurce=resource;
        this.classList = classList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();

        if (convertView == null){
            convertView = inflater.inflate(R.layout.item_class,null);
            viewHolder.id = (TextView) convertView.findViewById(R.id.tvID);
            viewHolder.maLop = (TextView) convertView.findViewById(R.id.tvMaLop);
            viewHolder.tenLop = (TextView) convertView.findViewById(R.id.tvTenLop);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ClassStudent aClass = classList.get(position);
        viewHolder.id.setText(String.valueOf(aClass.getId()));
        viewHolder.maLop.setText(aClass.getMaLop());
        viewHolder.tenLop.setText(aClass.getTenLop());
        return convertView;
    }

    public class ViewHolder{
        TextView id, maLop, tenLop;
    }
}
