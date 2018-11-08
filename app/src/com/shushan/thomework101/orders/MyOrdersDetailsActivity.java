package com.shushan.thomework101.orders;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.orders.OrdersDetails;
import com.shushan.thomework101.HttpHelper.service.presenter.OrdersDetailsPresenter;
import com.shushan.thomework101.HttpHelper.service.view.OrdersDetailsView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.OrdersGridviewAdapter;
import com.shushan.thomework101.banner.ImageViewRoundRect;
import com.shushan.thomework101.base.BaseActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.text.ParseException;

import butterknife.Bind;

public class MyOrdersDetailsActivity extends BaseActivity implements View.OnClickListener{
    @Bind(R.id.tv_actionbar)
    TextView tv_actionbar;
    @Bind(R.id.tv_right)
    TextView tv_right;
    @Bind(R.id.iv_actionbar_left)
    ImageView iv_actionbar_left;
    @Bind(R.id.view_video)
    ImageViewRoundRect view_video;
    @Bind(R.id.view_video_bg)
    ImageView view_video_bg;
    @Bind(R.id.tv_tutorship)
    TextView tv_tutorship;
    @Bind(R.id.tv_tutorship_state)
    TextView tv_tutorship_state;
    @Bind(R.id.iv_teacher_visa)
    ImageViewRoundRect iv_teacher_visa;
    @Bind(R.id.tv_teacher_name)
    TextView tv_teacher_name;
    @Bind(R.id.tv_teacher_class)
    TextView tv_teacher_class;

    @Bind(R.id.iv_video)
    ImageView iv_video;

    @Bind(R.id.tv_tutorship_money)
    TextView tv_tutorship_money;
    @Bind(R.id.tv_tutorship_money_default)
    TextView tv_tutorship_money_default;

    @Bind(R.id.tv_amount_paid)
    TextView tv_amount_paid;
    @Bind(R.id.tv_amount_paid_default)
    TextView tv_amount_paid_default;

    @Bind(R.id.tv_tutorship_orders_num)
    TextView tv_tutorship_orders_num;
    @Bind(R.id.tv_tutorship_orders_num_default)
    TextView tv_tutorship_orders_num_default;

    @Bind(R.id.tv_tutorship_orders_time)
    TextView tv_tutorship_orders_time;
    @Bind(R.id.tv_tutorship_orders_time_default)
    TextView tv_tutorship_orders_time_default;

    @Bind(R.id.tv_tutorship_pay_way)
    TextView tv_tutorship_pay_way;
    @Bind(R.id.tv_tutorship_pay_way_default)
    TextView tv_tutorship_pay_way_default;

    @Bind(R.id.tv_tutorship_pay_time)
    TextView tv_tutorship_pay_time;
    @Bind(R.id.tv_tutorship_pay_time_default)
    TextView tv_tutorship_pay_time_default;

    @Bind(R.id.tv_tutorship_duration)
    TextView tv_tutorship_duration;
    @Bind(R.id.tv_tutorship_duration_default)
    TextView tv_tutorship_duration_default;


    @Bind(R.id.ll_mine_tutorship_gridview)
    LinearLayout ll_mine_tutorship_gridview;
    @Bind(R.id.gridView_mine_tutorship)
    GridView gridView_mine_tutorship;

    private String oid;
    private int mine_order_label;
    private String token;
    private int tid;
    private OrdersDetailsPresenter ordersDetailsPresenter;
    private OrdersGridviewAdapter ordersGridviewAdapter;

    @Override
    protected void initContentView() {
        changeStatusBarTextImgColor(true);
        setContentView(R.layout.activity_my_orders_details);
    }

    @Override
    protected void initViews() {
        tv_actionbar.setText(getResources().getString(R.string.order_details));
        tv_actionbar.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
        TextPaint textPaint=tv_actionbar.getPaint();
        textPaint.setFakeBoldText(true);
        tv_right.setVisibility(View.VISIBLE);
        tv_right.setText(getResources().getString(R.string.orders_complain));
        view_video.setType(ImageViewRoundRect.TYPE_ROUND);
        view_video.setRoundRadius((int)getResources().getDimension(R.dimen.x2));
        ordersDetailsPresenter = new OrdersDetailsPresenter(this);
        ordersDetailsPresenter.onCreate(Constants.BASE_URL);
        ordersDetailsPresenter.attachView(ordersDetailsView);
    }

    @Override
    protected void initEvents() {
        iv_actionbar_left.setOnClickListener(this);
    }

    @Override
    protected void initData() {
        Intent intent=getIntent();
        if(intent!=null){
            oid = intent.getStringExtra("oid");
            LogUtils.d("oid:"+ oid);
        }
        SharedPreferences sharedPreferences=mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        tid = sharedPreferences.getInt("tid", 0);
        token = sharedPreferences.getString("token", "");
        mine_order_label = sharedPreferences.getInt("mine_order_label",1);
        if(!TextUtils.isEmpty(token)&&!TextUtils.isEmpty(token)&&!TextUtils.isEmpty(oid)){
            ordersDetailsPresenter.getOrdersDetailsMsg(tid +"", token,oid,mine_order_label+"");
        }

    }

    private OrdersDetailsView ordersDetailsView=new OrdersDetailsView() {
        @Override
        public void onSuccess(OrdersDetails ordersDetails) {

            if(ordersDetails.getData().getLabel()==1){
                if(ordersDetails.getData().getOrd_status()==0) {
                    tv_tutorship_state.setText(getResources().getString(R.string.orders_checking));

                }
                tv_tutorship_money.setVisibility(View.VISIBLE);
                tv_tutorship_money.setText(getResources().getString(R.string.orders_check_money));
                tv_tutorship.setText(getResources().getString(R.string.orders_homework_check));
                view_video_bg.setVisibility(View.GONE);
                view_video.setVisibility(View.GONE);
                iv_video.setVisibility(View.GONE);
                tv_tutorship_duration.setVisibility(View.GONE);
                tv_tutorship_duration_default.setVisibility(View.GONE);
                ll_mine_tutorship_gridview.setVisibility(View.VISIBLE);
                gridView_mine_tutorship.setVisibility(View.VISIBLE);
                ordersGridviewAdapter = new OrdersGridviewAdapter(ordersDetails.getData().getJob_pic(),MyOrdersDetailsActivity.this);
                gridView_mine_tutorship.setAdapter(ordersGridviewAdapter);

            }else{
                if(ordersDetails.getData().getOrd_status()==0){
                    tv_tutorship_state.setText(getResources().getString(R.string.orders_tutorship_ing));


                }
                tv_tutorship_money.setVisibility(View.VISIBLE);
                tv_tutorship_money.setText(getResources().getString(R.string.orders_tutorship_money));
                tv_tutorship.setText(getResources().getString(R.string.orders_homework_help));
                view_video_bg.setVisibility(View.VISIBLE);
                view_video.setVisibility(View.VISIBLE);
                iv_video.setVisibility(View.VISIBLE);
                tv_tutorship_duration.setVisibility(View.VISIBLE);
                tv_tutorship_duration_default.setVisibility(View.VISIBLE);
                ll_mine_tutorship_gridview.setVisibility(View.GONE);
                gridView_mine_tutorship.setVisibility(View.GONE);

            }

            if(ordersDetails.getData().getOrd_status()==0){

                tv_tutorship_money_default.setVisibility(View.VISIBLE);
                tv_tutorship_money_default.setText("￥"+ordersDetails.getData().getPrice());
                tv_amount_paid.setVisibility(View.GONE);
                tv_amount_paid_default.setVisibility(View.GONE);
                tv_tutorship_orders_num.setVisibility(View.GONE);
                tv_tutorship_orders_num_default.setVisibility(View.GONE);
                tv_tutorship_orders_time.setVisibility(View.GONE);
                tv_tutorship_orders_time_default.setVisibility(View.GONE);
                tv_tutorship_pay_way.setVisibility(View.GONE);
                tv_tutorship_pay_way_default.setVisibility(View.GONE);
                tv_tutorship_pay_time.setVisibility(View.GONE);
                tv_tutorship_pay_time_default.setVisibility(View.GONE);


            }else if(ordersDetails.getData().getOrd_status()==1){

                tv_tutorship_money_default.setVisibility(View.VISIBLE);
                tv_tutorship_money_default.setText("￥"+ordersDetails.getData().getPrice());
                tv_amount_paid.setVisibility(View.VISIBLE);
                tv_amount_paid_default.setVisibility(View.VISIBLE);
                tv_amount_paid_default.setText("￥"+ordersDetails.getData().getPay_money());
                tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                try {
                    tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_tutorship_pay_way.setVisibility(View.GONE);
                tv_tutorship_pay_way_default.setVisibility(View.GONE);
                tv_tutorship_pay_time.setVisibility(View.GONE);
                tv_tutorship_pay_time_default.setVisibility(View.GONE);


            }else if(ordersDetails.getData().getOrd_status()==2){

                tv_tutorship_money_default.setVisibility(View.VISIBLE);
                tv_tutorship_money_default.setText("￥"+ordersDetails.getData().getPrice());
                tv_amount_paid.setVisibility(View.VISIBLE);
                tv_amount_paid_default.setVisibility(View.VISIBLE);
                tv_amount_paid_default.setText("￥"+ordersDetails.getData().getPay_money());
                tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                try {
                    tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_tutorship_pay_way.setVisibility(View.VISIBLE);
                tv_tutorship_pay_way_default.setVisibility(View.VISIBLE);

                if(ordersDetails.getData().getCate()==1){
                    tv_tutorship_pay_way_default.setText(getResources().getString(R.string.orders_pay_weixin));
                }else if(ordersDetails.getData().getCate()==2){
                    tv_tutorship_pay_way_default.setText(getResources().getString(R.string.orders_pay_alipay));
                }else{
                    tv_tutorship_pay_way_default.setText(getResources().getString(R.string.orders_pay_balance));
                }

                try {
                    if(ordersDetails.getData().getPay_time()!=0){
                        tv_tutorship_pay_time.setVisibility(View.VISIBLE);
                        tv_tutorship_pay_time_default.setVisibility(View.VISIBLE);
                        tv_tutorship_pay_time_default.setText(Utils.longToString(ordersDetails.getData().getPay_time()*1000L,"yyyy-MM-dd HH:mm"));
                    }else{
                        tv_tutorship_pay_time.setVisibility(View.GONE);
                        tv_tutorship_pay_time_default.setVisibility(View.GONE);
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }

            }else{
                tv_tutorship_money.setVisibility(View.GONE);
                tv_tutorship_money_default.setVisibility(View.GONE);
                tv_amount_paid.setVisibility(View.GONE);
                tv_amount_paid_default.setVisibility(View.GONE);
                tv_tutorship_orders_num.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setVisibility(View.VISIBLE);
                tv_tutorship_orders_num_default.setText(ordersDetails.getData().getOid());
                tv_tutorship_orders_time.setVisibility(View.VISIBLE);
                tv_tutorship_orders_time_default.setVisibility(View.VISIBLE);
                try {
                    tv_tutorship_orders_time_default.setText(Utils.longToString((ordersDetails.getData().getCreate_time()*1000L),"yyyy-MM-dd HH:mm"));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                tv_tutorship_pay_way.setVisibility(View.GONE);
                tv_tutorship_pay_way_default.setVisibility(View.GONE);
                tv_tutorship_pay_time.setVisibility(View.GONE);
                tv_tutorship_pay_time_default.setVisibility(View.GONE);

            }

            tv_teacher_class.setText(ordersDetails.getData().getGrade()+"/"+ordersDetails.getData().getSubject());
            tv_teacher_name.setText(ordersDetails.getData().getUsername());
            RequestOptions options = new RequestOptions()
                    .error(R.drawable.visa)
                    .diskCacheStrategy(DiskCacheStrategy.ALL);
            Glide.with(MyOrdersDetailsActivity.this).load(ordersDetails.getData().getTrait()).apply(options).into(iv_teacher_visa);

            if(ordersDetails.getData().getOrd_status()==1){
                tv_tutorship_state.setText(getResources().getString(R.string.orders_obligation));
            }else if(ordersDetails.getData().getOrd_status()==2){
                tv_tutorship_state.setText(getResources().getString(R.string.orders_complete));
            }else if(ordersDetails.getData().getOrd_status()==3){
                tv_tutorship_state.setText(getResources().getString(R.string.orders_cancel));
            }

        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_actionbar_left:
                finish();
                break;

        }
    }
}
