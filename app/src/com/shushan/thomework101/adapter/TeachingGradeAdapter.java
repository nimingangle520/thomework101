package com.shushan.thomework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.bean.Grade;

import java.util.ArrayList;

public class TeachingGradeAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Grade> gradeArrayList;
    public TeachingGradeAdapter(Context context, ArrayList<Grade> gradeArrayList) {
        this.context=context;
        this.gradeArrayList=gradeArrayList;
    }

    @Override
    public int getCount() {
        return gradeArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return gradeArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.teaching_grade_list_item, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_teaching_grade = (ImageView) convertView.findViewById(R.id.iv_teaching_grade);
            viewHolder.tv_teaching_grade = (TextView) convertView.findViewById(R.id.tv_teaching_grade);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(gradeArrayList!=null&&gradeArrayList.size()>0){
            viewHolder.tv_teaching_grade.setText(gradeArrayList.get(position).getGrade());
            if(gradeArrayList.get(position).isSelected()){
                viewHolder.iv_teaching_grade.setBackgroundResource(R.drawable.payment_method_choose);
            }else{
                viewHolder.iv_teaching_grade.setBackgroundResource(R.drawable.teaching_grade_circle);
            }
        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_teaching_grade;
        TextView tv_teaching_grade;
    }
}
