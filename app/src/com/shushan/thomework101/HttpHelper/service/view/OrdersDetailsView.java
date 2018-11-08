package com.shushan.thomework101.HttpHelper.service.view;


import com.shushan.thomework101.HttpHelper.service.entity.orders.OrdersDetails;

public interface OrdersDetailsView extends View {
    void onSuccess(OrdersDetails ordersDetails);
    void onError(String result);
}
