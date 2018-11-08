package com.shushan.thomework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.bean.HomeworkOrdersList;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;

/**
 * Created by LIMING on 2017/1/13.
 */
public class HomepageListViewAdapter extends BaseAdapter {

    private ArrayList<HomeworkOrdersList> homeworkOrdersLists;
    private Context context;
    private ArrayList<HomepageData> homepageData = new ArrayList();

    public HomepageListViewAdapter(ArrayList<HomeworkOrdersList> homeworkOrdersLists, Context context) {
        this.homeworkOrdersLists = homeworkOrdersLists;
        this.context = context;
        LogUtils.e(homeworkOrdersLists.toString());

    }

    @Override
    public int getCount() {
        if (homeworkOrdersLists != null && homeworkOrdersLists.size() > 0) {
            return homeworkOrdersLists.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return homeworkOrdersLists.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        homepageData = homeworkOrdersLists.get(position).getHomepageData();
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.homepage_listview_item, parent,false);
            viewHolder = new ViewHolder();
            viewHolder.rl_homework_tutorship= (RelativeLayout) convertView.findViewById(R.id.rl_homework_tutorship);
            viewHolder.tv_homepage_title = (TextView) convertView.findViewById(R.id.tv_homepage_title);
            viewHolder.iv_homepage_refresh= (ImageView) convertView.findViewById(R.id.iv_homepage_refresh);
            viewHolder.gridView = (GridView) convertView.findViewById(R.id.gridView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if (homeworkOrdersLists != null && homeworkOrdersLists.size() > 0) {
            if(homeworkOrdersLists.get(position).getType()==0){
                viewHolder.rl_homework_tutorship.setVisibility(View.GONE);

            }else if(homeworkOrdersLists.get(position).getType()==2){
                viewHolder.rl_homework_tutorship.setVisibility(View.VISIBLE);
                viewHolder.iv_homepage_refresh.setVisibility(View.INVISIBLE);
                viewHolder.tv_homepage_title.setText(context.getResources().getString(R.string.homepage_homework_have_order));


            }else if(homeworkOrdersLists.get(position).getType()==12){
                viewHolder.rl_homework_tutorship.setVisibility(View.VISIBLE);
                viewHolder.iv_homepage_refresh.setVisibility(View.VISIBLE);
                viewHolder.tv_homepage_title.setText(context.getResources().getString(R.string.homepage_homework_book));
            }else{
                viewHolder.rl_homework_tutorship.setVisibility(View.VISIBLE);
                viewHolder.iv_homepage_refresh.setVisibility(View.VISIBLE);
                viewHolder.tv_homepage_title.setText(context.getResources().getString(R.string.homepage_homework_real_time));
            }
            HomepageGrideViewAdapter adapter = new HomepageGrideViewAdapter(homepageData, context);
            viewHolder.gridView.setAdapter(adapter);
        }
        return convertView;
    }

    public class ViewHolder {
        public RelativeLayout rl_homework_tutorship;
        public TextView tv_homepage_title;
        public ImageView iv_homepage_refresh;
        public GridView gridView;
    }
}
