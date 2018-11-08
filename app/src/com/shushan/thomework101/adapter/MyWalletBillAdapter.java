package com.shushan.thomework101.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.shushan.thomework101.HttpHelper.service.entity.wallet.Detail;
import com.shushan.thomework101.R;
import com.shushan.thomework101.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;

public class MyWalletBillAdapter extends RecyclerView.Adapter<MyWalletBillAdapter.MyViewHolder> {

    private ArrayList<Detail> mDatas;
    private LayoutInflater mInflater;
    private OnItemClickLitener mOnItemClickLitener;
    private Context context;
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }


    public MyWalletBillAdapter(Context context, ArrayList<Detail> datas) {
        mInflater = LayoutInflater.from(context);
        mDatas = datas;
        this.context=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        MyViewHolder holder = new MyViewHolder(mInflater.inflate(
                R.layout.wallet_bill_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        if(mDatas.get(position).getLabel()==1){

            holder.iv_bill_icon.setImageResource(R.drawable.bill_inspect);
            holder.tv_bill_type.setText(context.getResources().getString(R.string.homepage_homework_check));
        }else{
            holder.iv_bill_icon.setImageResource(R.drawable.bill_coach);
            holder.tv_bill_type.setText(context.getResources().getString(R.string.homepage_homework_tutorship));
        }
        try {
            holder.tv_bill_time.setText(Utils.longToString(mDatas.get(position).getIncome_time()*1000L,context.getResources().getString(R.string.wallet_time_format)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(mDatas.get(position).getIncome()>0){
            holder.tv_bill_money.setText("+"+mDatas.get(position).getIncome());
            holder.tv_bill_money.setTextColor(context.getColor(R.color.font_color_select));
        }else{
            holder.tv_bill_money.setText("-"+mDatas.get(position).getIncome());
            holder.tv_bill_money.setTextColor(context.getColor(R.color.black));
        }


        // 如果设置了回调，则设置点击事件
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView, pos);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void removeData(int position) {
        mDatas.remove(position);
        notifyItemRemoved(position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_bill_type,tv_bill_time,tv_bill_money;
        ImageView iv_bill_icon;
        public MyViewHolder(View view) {
            super(view);

            iv_bill_icon= (ImageView) view.findViewById(R.id.iv_bill_icon);
            tv_bill_type = (TextView) view.findViewById(R.id.tv_bill_type);
            tv_bill_time = (TextView) view.findViewById(R.id.tv_bill_time);
            tv_bill_money = (TextView) view.findViewById(R.id.tv_bill_money);
        }
    }
}
