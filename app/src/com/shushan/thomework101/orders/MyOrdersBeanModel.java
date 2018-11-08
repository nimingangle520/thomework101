package com.shushan.thomework101.orders;

import android.content.Context;

import com.shushan.thomework101.R;
import com.shushan.thomework101.bean.MyOrdersBean;

import java.util.ArrayList;

public class MyOrdersBeanModel {
    private Context context;
    private ArrayList<MyOrdersBean> myOrdersBeanArrayList = new ArrayList<>();

    public MyOrdersBeanModel(Context context) {
        this.context = context;
    }

    public ArrayList<MyOrdersBean> getMyOrdersBeanList() {
        MyOrdersBean myOrdersBean=new MyOrdersBean();
        myOrdersBean.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
        add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBean.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBean.setTime("2018-03-03 08:04");
        myOrdersBean.setStudIcon(R.drawable.visa);
        myOrdersBean.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBean.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBean.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBean);

        MyOrdersBean myOrdersBeanTwo=new MyOrdersBean();
        myOrdersBeanTwo.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
            add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBeanTwo.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBeanTwo.setTime("2018-03-03 08:04");
        myOrdersBeanTwo.setStudIcon(R.drawable.visa);
        myOrdersBeanTwo.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBeanTwo.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBeanTwo.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBeanTwo);

        MyOrdersBean myOrdersBeanSix=new MyOrdersBean();
        myOrdersBeanSix.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
            add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBeanSix.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBeanSix.setTime("2018-03-03 08:04");
        myOrdersBeanSix.setStudIcon(R.drawable.visa);
        myOrdersBeanSix.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBeanSix.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBeanSix.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBeanSix);

        MyOrdersBean myOrdersBeanThree=new MyOrdersBean();
        myOrdersBeanThree.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
            add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBeanThree.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBeanThree.setTime("2018-03-03 08:04");
        myOrdersBeanThree.setStudIcon(R.drawable.visa);
        myOrdersBeanThree.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBeanThree.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBeanThree.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBeanThree);

        MyOrdersBean myOrdersBeanFour=new MyOrdersBean();
        myOrdersBeanFour.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
            add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBeanFour.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBeanFour.setTime("2018-03-03 08:04");
        myOrdersBeanFour.setStudIcon(R.drawable.visa);
        myOrdersBeanFour.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBeanFour.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBeanFour.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBeanFour);

        MyOrdersBean myOrdersBeanFive=new MyOrdersBean();
        myOrdersBeanFive.setHomeworkIcons(new ArrayList<Integer>(){{add(R.drawable.homework);add(R.drawable.homework);
            add(R.drawable.homework);add(R.drawable.homework);add(R.drawable.homework);}});
        myOrdersBeanFive.setState(context.getResources().getString(R.string.orders_obligation));
        myOrdersBeanFive.setTime("2018-03-03 08:04");
        myOrdersBeanFive.setStudIcon(R.drawable.visa);
        myOrdersBeanFive.setStuName(context.getResources().getString(R.string.homepage_teacher_name));
        myOrdersBeanFive.setGrade(context.getResources().getString(R.string.tutorship_teaching_grade));
        myOrdersBeanFive.setMoney("￥88.00");
        myOrdersBeanArrayList.add(myOrdersBeanFive);

        return myOrdersBeanArrayList;
    }
}
