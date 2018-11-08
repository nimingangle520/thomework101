package com.shushan.thomework101.login;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.TeachingGradeAdapter;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.bean.Grade;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;

import butterknife.Bind;

public class TeachingGradeActivity extends BaseActivity implements AdapterView.OnItemClickListener{
@Bind(R.id.lv_grade)
    ListView lv_grade;
    private ArrayList<Grade> gradeList;
    private String[] grade;
    private TeachingGradeAdapter teachingGradeAdapter;

    @Override
    protected void initContentView() {
        setContentView(R.layout.activity_teaching_grade);
        changeStatusBarTextImgColor(true);
    }

    @Override
    protected void initViews() {
        gradeList = new ArrayList();
    }

    @Override
    protected void initEvents() {

    }

    @Override
    protected void initData() {
        grade = this.getResources().getStringArray(R.array.grade);

        for (int i = 0; i < grade.length ; i++) {
            gradeList.add(new Grade(grade[i],false));
        }
        teachingGradeAdapter = new TeachingGradeAdapter(this, gradeList);
        lv_grade.setAdapter(teachingGradeAdapter);
        lv_grade.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        LogUtils.i("position:"+i);
        if(gradeList.get(i).isSelected()){
            gradeList.get(i).setSelected(false);
        }else{
            gradeList.get(i).setSelected(true);
        }
        teachingGradeAdapter.notifyDataSetChanged();
    }
}
