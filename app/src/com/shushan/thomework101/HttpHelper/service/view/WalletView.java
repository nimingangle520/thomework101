package com.shushan.thomework101.HttpHelper.service.view;

import com.shushan.thomework101.HttpHelper.service.entity.wallet.Wallet;

public interface WalletView extends View {

    void onSuccess(Wallet wallet);
    void onError(String result);
}
