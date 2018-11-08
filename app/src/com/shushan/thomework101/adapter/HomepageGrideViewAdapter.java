package com.shushan.thomework101.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.GlideApp;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.HttpHelper.service.entity.orders.GoCheck;
import com.shushan.thomework101.HttpHelper.service.presenter.GoCheckPresenter;
import com.shushan.thomework101.HttpHelper.service.view.GoCheckView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.homepage.HomeworkOrdersDetailsActivity;
import com.shushan.thomework101.homepage.OvalImageView;
import com.shushan.thomework101.tutorship.StartTutorshipActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by LIMING on 2017/1/13.
 */
public class HomepageGrideViewAdapter extends BaseAdapter {
    private ArrayList<HomepageData> homepageDataList;
    private Context context;
    //private CornerTransform transformation;
    private final GoCheckPresenter goCheckPresenter;
    private final SharedPreferences sharedPreferences;
    private final int tid;
    private final String token;
    private HomepageData homepageData;

    public HomepageGrideViewAdapter(ArrayList<HomepageData> homepageDataList, Context context) {
        this.homepageDataList = homepageDataList;
        this.context = context;
        //transformation = new CornerTransform(context, Utils.dip2px(4));
        //只是绘制左上角和右上角圆角
        //transformation.setExceptCorner(false, false, true, true);
        goCheckPresenter = new GoCheckPresenter(context);
        goCheckPresenter.onCreate(Constants.BASE_URL);
        goCheckPresenter.attachView(goCheckView);
        sharedPreferences = context.getSharedPreferences("info",MODE_PRIVATE);
        tid = sharedPreferences.getInt("tid",0);
        token = sharedPreferences.getString("token","");
    }

    private GoCheckView goCheckView = new GoCheckView() {
        @Override
        public void onSuccess(GoCheck goCheck) {

            if(goCheck!=null&&goCheck.getError()==0){

                Intent intent=new Intent(context, StartTutorshipActivity.class);
                intent.putExtra("oid",goCheck.getData().getOid());
                intent.putExtra("homepageDate",homepageData);
                context.startActivity(intent);
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    public int getCount() {
        return homepageDataList.size();
    }

    @Override
    public Object getItem(int position) {
        return homepageDataList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.homepage_grideview_item, parent,false);

            viewHolder.iv_homepage_homework_icon = (OvalImageView) convertView.findViewById(R.id.iv_homepage_homework_icon);
            viewHolder.iv_homepage_student_icon = (ImageView) convertView.findViewById(R.id.iv_homepage_student_icon);
            viewHolder.tv_homepage_student_name = (TextView) convertView.findViewById(R.id.tv_homepage_student_name);
            viewHolder.tv_homepage_student_grade = (TextView) convertView.findViewById(R.id.tv_homepage_student_grade);
            viewHolder.tv_homework_money = (TextView) convertView.findViewById(R.id.tv_homework_money);
            viewHolder.btn_homework_contact= (Button) convertView.findViewById(R.id.btn_homework_contact);
            viewHolder.rl_homework_gradeview= (RelativeLayout) convertView.findViewById(R.id.rl_homework_gradeview);
            viewHolder.tv_tutorship_time=convertView.findViewById(R.id.tv_tutorship_time);
            viewHolder.tv_homework_go_tutorship=convertView.findViewById(R.id.tv_homework_go_tutorship);
            viewHolder.tv_homework_wait_go_tutorship=convertView.findViewById(R.id.tv_homework_wait_go_tutorship);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        homepageData = homepageDataList.get(position);

        GlideApp.with(context).load(homepageData.getJob_pic()[0])
                .into(viewHolder.iv_homepage_homework_icon);

        GlideApp.with(context).load(homepageData.getTrait()).error(R.drawable.visa).
                into(viewHolder.iv_homepage_student_icon);
         viewHolder.tv_homepage_student_name.setText(homepageData.getUsername());
         viewHolder.tv_homepage_student_grade.setText(homepageData.getGrade());


         if(homepageData.getLabel()==1){
             viewHolder.btn_homework_contact.setVisibility(View.VISIBLE);
             viewHolder.btn_homework_contact.setText(context.getResources().getString(R.string.homepage_go_check));
             viewHolder.tv_tutorship_time.setVisibility(View.GONE);
             viewHolder.tv_homework_money.setText("￥"+ homepageData.getPrice()+context.getResources().getString(R.string.dollar));
             viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.identity_rb_font));
             viewHolder.tv_homework_go_tutorship.setVisibility(View.GONE);
             viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.GONE);

         }

         if(homepageData.getIs_receive()==2){
             viewHolder.btn_homework_contact.setVisibility(View.GONE);
             viewHolder.tv_tutorship_time.setVisibility(View.VISIBLE);
             viewHolder.tv_homework_money.setText("￥"+ homepageData.getPrice()+context.getResources().getString(R.string.dollar_per_minute));


             try {
                 viewHolder.tv_tutorship_time.setText(Utils.longToString(homepageData.getStart_time()*1000L,"MM-dd HH:mm"));
             } catch (ParseException e) {
                 e.printStackTrace();
             }
             /*if(homepageData.getLabel()==2){
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.VISIBLE);
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.identity_rb_font));

             }else{
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.VISIBLE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.homepage_homework_gridview_go_help));
             }*/

             if(System.currentTimeMillis()<homepageData.getStart_time()*1000L){
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.VISIBLE);
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.identity_rb_font));

             }else{
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.VISIBLE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.homepage_homework_gridview_go_help));
             }

         }else{
             if(homepageData.getLabel()==2){
                 viewHolder.btn_homework_contact.setVisibility(View.VISIBLE);
                 viewHolder.btn_homework_contact.setText(context.getResources().getString(R.string.homepage_take_orders));
                 viewHolder.tv_tutorship_time.setVisibility(View.VISIBLE);
                 try {
                     viewHolder.tv_tutorship_time.setText(Utils.longToString(homepageData.getStart_time()*1000L,"MM-dd HH:mm"));
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
                 viewHolder.tv_homework_money.setText("￥"+ homepageData.getPrice()+context.getResources().getString(R.string.dollar_per_minute));
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.identity_rb_font));
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.GONE);
             }
             if(homepageData.getLabel()==3){
                 viewHolder.btn_homework_contact.setVisibility(View.VISIBLE);
                 viewHolder.btn_homework_contact.setText(context.getResources().getString(R.string.homepage_take_orders));
                 viewHolder.tv_tutorship_time.setVisibility(View.GONE);
                 viewHolder.tv_homework_money.setText("￥"+ homepageData.getPrice()+context.getResources().getString(R.string.dollar_per_minute));
                 viewHolder.tv_homework_money.setTextColor(context.getColor(R.color.identity_rb_font));
                 viewHolder.tv_homework_go_tutorship.setVisibility(View.GONE);
                 viewHolder.tv_homework_wait_go_tutorship.setVisibility(View.GONE);
             }

         }
         viewHolder.rl_homework_gradeview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HomeworkOrdersDetailsActivity.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("homepageData", homepageDataList.get(position));
                intent.putExtras(bundle);
                context.startActivity(intent);
                LogUtils.d("\nposition:"+position+"\nhomepageData:"+homepageDataList.get(position).toString());
                }

        });

         viewHolder.btn_homework_contact.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(homepageData.getLabel()==1){

                    goCheckPresenter.getGoChecksMsg(tid+"",token, homepageData.getJob_id()+"");

                 }else{

                 }
             }
         });

        return convertView;
    }

    public class ViewHolder {
        public RelativeLayout rl_homework_gradeview;
        public ImageView iv_homepage_student_icon ;
        public OvalImageView iv_homepage_homework_icon;
        public TextView tv_homepage_student_name,tv_homepage_student_grade,
                tv_homework_money,tv_tutorship_time,
                tv_homework_go_tutorship,tv_homework_wait_go_tutorship;
        public Button btn_homework_contact;
    }
}
