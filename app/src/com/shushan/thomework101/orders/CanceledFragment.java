package com.shushan.thomework101.orders;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import android.view.View;
import android.widget.ListView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.orders.Orders;
import com.shushan.thomework101.HttpHelper.service.entity.orders.OrdersData;
import com.shushan.thomework101.HttpHelper.service.presenter.OrdersPresenter;
import com.shushan.thomework101.HttpHelper.service.view.OrdersView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.MyOrdersListViewAdapter;
import com.shushan.thomework101.base.BaseFragment;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;


public class CanceledFragment extends BaseFragment {
    @Bind(R.id.lv_orders_cancel)
    ListView lv_orders_cancel;


    private MyOrdersListViewAdapter myOrdersListViewAdapter;
    private SharedPreferences sharedPreferences;
    private int tid;
    private int mine_order_label;
    private String token;
    private Map<String, String> mapRequest;
    private OrdersPresenter ordersCompletePresenter;
    private ArrayList<OrdersData> ordersDataArrayList;
    public CanceledFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_canceled;
    }

    @Override
    public void initViews(View view) {
        ordersDataArrayList=new ArrayList<>();
        mapRequest = new HashMap<>();
        ordersCompletePresenter = new OrdersPresenter(getContext());
        ordersCompletePresenter.onCreate(Constants.BASE_URL);
        ordersCompletePresenter.attachView(ordersView);
    }

    @Override
    protected void initData() {
        sharedPreferences = mContext.getSharedPreferences("info", Context.MODE_PRIVATE);
        mine_order_label = sharedPreferences.getInt("mine_order_label",1);
        tid = sharedPreferences.getInt("tid", 0);
        token = sharedPreferences.getString("token", "");
    }

    @Override
    public void initEvents() {
        if(!TextUtils.isEmpty(token)&&!TextUtils.isEmpty(mine_order_label+"")){
            mapRequest.put("tid",tid+"");
            mapRequest.put("token",token);
            mapRequest.put("label",mine_order_label+"");
            //cate = all, doing, nopay,complete,cancel
            mapRequest.put("page","1");
            mapRequest.put("cata","all");
            ordersCompletePresenter.getOrdersMsg(mapRequest);
        }
    }

    private OrdersView ordersView=new OrdersView() {
        @Override
        public void onSuccess(final Orders orders) {
            if(orders!=null){
                LogUtils.d(orders.toString());
                if(orders.getData()!=null&&orders.getData().size()>0){
                    ArrayList<OrdersData> ordersDatas=orders.getData();
                    for (int i = 0; i <ordersDatas.size() ; i++) {
                        if(ordersDatas.get(i).getStatus()==3){
                            ordersDataArrayList.add(ordersDatas.get(i));
                        }
                    }
                    myOrdersListViewAdapter = new MyOrdersListViewAdapter(mContext, ordersDataArrayList);
                    lv_orders_cancel.setAdapter(myOrdersListViewAdapter);

                }

            }
        }

        @Override
        public void onError(String result) {

        }
    };

}
