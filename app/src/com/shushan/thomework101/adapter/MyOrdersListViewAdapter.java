package com.shushan.thomework101.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.shushan.thomework101.GlideApp;
import com.shushan.thomework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.thomework101.R;
import com.shushan.thomework101.banner.ImageViewRoundRect;
import com.shushan.thomework101.orders.MyOrdersDetailsActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;

public class MyOrdersListViewAdapter extends BaseAdapter{
    private Context context;
    private ArrayList<OrdersData> myOrdersArrayList;
    public MyOrdersListViewAdapter(Context context, ArrayList<OrdersData> myOrdersArrayList) {
        this.context=context;
        this.myOrdersArrayList=myOrdersArrayList;
        if(myOrdersArrayList!=null&&myOrdersArrayList.size()>0){

            LogUtils.e("myOrdersArrayList.size():"+myOrdersArrayList.size());
        }
    }

    @Override
    public int getCount() {
        return myOrdersArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return myOrdersArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
       ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.orders_item, viewGroup,false);
            viewHolder = new ViewHolder();
            viewHolder.iv_orders_stu_visa = (ImageViewRoundRect) convertView.findViewById(R.id.iv_orders_stu_visa);
            viewHolder.tv_orders_time = (TextView) convertView.findViewById(R.id.tv_orders_time);
            viewHolder.tv_orders_state = (TextView) convertView.findViewById(R.id.tv_orders_state);
            viewHolder.tv_orders_stu_name = (TextView) convertView.findViewById(R.id.tv_orders_stu_name);
            viewHolder.tv_orders_stu_grade = (TextView) convertView.findViewById(R.id.tv_orders_stu_grade);
            viewHolder.tv_orders_obligation_money_details = (TextView) convertView.findViewById(R.id.tv_orders_obligation_money_details);
            viewHolder.tv_orders_obligation_money=convertView.findViewById(R.id.tv_orders_obligation_money);
            viewHolder.ll_orders_item=convertView.findViewById(R.id.ll_orders_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();

        }
        if(myOrdersArrayList!=null&&myOrdersArrayList.size()>0){

            GlideApp.with(context).load(myOrdersArrayList.get(position).getTrait()).error(R.drawable.visa).into(viewHolder.iv_orders_stu_visa);

            int create_time=myOrdersArrayList.get(position).getCreate_time();
            try {
                viewHolder.tv_orders_time.setText(Utils.longToString(create_time*1000L,"yyyy-MM-dd HH:mm"));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            viewHolder.tv_orders_stu_name.setText(myOrdersArrayList.get(position).getUsername());
            viewHolder.tv_orders_stu_grade.setText(myOrdersArrayList.get(position).getGrade()+"/"+myOrdersArrayList.get(position).getSubject());

            //0辅导中检查中1待付款2已付款3已取消
            switch (myOrdersArrayList.get(position).getStatus()){
                case 0:
                    if(myOrdersArrayList.get(position).getLabel()==1){
                        viewHolder.tv_orders_state.setText(context.getResources().getString(R.string.orders_checking));
                    }else{
                        viewHolder.tv_orders_state.setText(context.getResources().getString(R.string.orders_tutorship_ing));
                    }
                    viewHolder.tv_orders_obligation_money.setVisibility(View.GONE);
                    viewHolder.tv_orders_obligation_money_details.setVisibility(View.GONE);
                    break;
                case 1:
                    viewHolder.tv_orders_obligation_money.setVisibility(View.VISIBLE);
                    viewHolder.tv_orders_obligation_money_details.setVisibility(View.VISIBLE);
                    viewHolder.tv_orders_state.setText(context.getResources().getString(R.string.orders_obligation));
                    viewHolder.tv_orders_obligation_money.setText(context.getResources().getString(R.string.orders_obligation_money));
                    viewHolder.tv_orders_obligation_money_details.setText("￥"+myOrdersArrayList.get(position).getPrice());

                    break;
                case 2:
                    viewHolder.tv_orders_obligation_money.setVisibility(View.VISIBLE);
                    viewHolder.tv_orders_obligation_money_details.setVisibility(View.VISIBLE);
                    viewHolder.tv_orders_state.setText(context.getResources().getString(R.string.orders_complete));
                    viewHolder.tv_orders_obligation_money.setText(context.getResources().getString(R.string.orders_paid_money));
                    viewHolder.tv_orders_obligation_money_details.setText("￥"+myOrdersArrayList.get(position).getPrice());
                    break;
                case 3:
                    viewHolder.tv_orders_obligation_money.setVisibility(View.GONE);
                    viewHolder.tv_orders_obligation_money_details.setVisibility(View.GONE);
                    viewHolder.tv_orders_state.setText(context.getResources().getString(R.string.orders_cancel));
                    break;
            }
            viewHolder.ll_orders_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(context, MyOrdersDetailsActivity.class);
                    intent.putExtra("oid",myOrdersArrayList.get(position).getOid());
                    context.startActivity(intent);
                }
            });

        }

        return convertView;
    }
    private class ViewHolder {
        LinearLayout ll_orders_item;
        TextView tv_orders_time;
        TextView tv_orders_state;
        ImageViewRoundRect iv_orders_stu_visa;
        TextView tv_orders_stu_name;
        TextView tv_orders_stu_grade;
        TextView tv_orders_obligation_money,tv_orders_obligation_money_details;
    }

}
