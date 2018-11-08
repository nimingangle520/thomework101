package com.shushan.thomework101.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.bean.HomepageMenusItem;

import java.util.ArrayList;

public class HomepageMenusAdapter extends BaseAdapter{

    private Context context;
    private ArrayList<HomepageMenusItem> menuArrayList;
    public HomepageMenusAdapter(Context context, ArrayList<HomepageMenusItem> menuArrayList) {
        this.context=context;
        this.menuArrayList=menuArrayList;
    }

    @Override
    public int getCount() {
        return menuArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return menuArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.homepage_menus_item, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_homepage_menus_icon = (ImageView) convertView.findViewById(R.id.iv_homepage_menus_icon);
            viewHolder.tv_homepage_menus_title = (TextView) convertView.findViewById(R.id.tv_homepage_menus_title);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(menuArrayList!=null&&menuArrayList.size()>0){
            viewHolder.tv_homepage_menus_title.setText(menuArrayList.get(position).getTitle());
            viewHolder.iv_homepage_menus_icon.setBackgroundResource(menuArrayList.get(position).getIconId());
        }
        return convertView;
    }
    private class ViewHolder {
        ImageView iv_homepage_menus_icon;
        TextView tv_homepage_menus_title;
    }
}
