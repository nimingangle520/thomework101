package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.wallet.WalletDetails;

public interface WalletDetailsView extends View{
    void onSuccess(WalletDetails walletDetails);
    void onError(String result);
}
