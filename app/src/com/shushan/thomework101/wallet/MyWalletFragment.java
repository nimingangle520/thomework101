package com.shushan.thomework101.wallet;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.wallet.Detail;
import com.shushan.thomework101.HttpHelper.service.entity.wallet.Wallet;
import com.shushan.thomework101.HttpHelper.service.entity.wallet.WalletDetails;
import com.shushan.thomework101.HttpHelper.service.presenter.WalletDetailsPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.WalletPresenter;
import com.shushan.thomework101.HttpHelper.service.view.WalletDetailsView;
import com.shushan.thomework101.HttpHelper.service.view.WalletView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.MyWalletBillAdapter;
import com.shushan.thomework101.base.BaseFragment;
import com.shushan.thomework101.homepage.HomepageActivity;
import com.shushan.thomework101.utils.LogUtils;
import com.shushan.thomework101.utils.Utils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.Bind;

import static android.content.Context.MODE_PRIVATE;

public class MyWalletFragment extends BaseFragment implements View.OnClickListener,BillFilterPopup.OnItemClickListener{
    @Bind(R.id.rv_wallet)
    RecyclerView rv_wallet;
    @Bind(R.id.iv_wallet_back)
    ImageView iv_wallet_back;
    @Bind(R.id.tv_wallet_balance_details)
    TextView tv_wallet_balance_details;
    @Bind(R.id.tv_wallet_bill_filter)
    TextView tv_wallet_bill_filter;
    @Bind(R.id.tv_wallet_bill_time)
    TextView tv_wallet_bill_time;
    @Bind(R.id.cdl_wallet)
    CoordinatorLayout cdl_wallet;


    private MyWalletBillAdapter myWalletBillAdapter;
    private WalletPresenter walletPresenter;
    private String token;
    private SharedPreferences sharedPreferences;
    private DisplayMetrics displayMetrics;
    private int width;
    private int height;
    private PopupWindow mPopupWindow;
    private View menuView;
    private WheelTimeSelect wheelTimeSelect;
    private TextView tv_cancel;
    private TextView tv_ensure;
    private BillFilterPopup billFilterPopup;
    private int mLabel;
    private String selectTime;
    private WalletDetailsPresenter walletDetailsPresenter;
    private ArrayList<Detail> detailArrayList;

    public MyWalletFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_my_wallet;
    }

    @Override
    public void initViews(View view) {
        detailArrayList = new ArrayList<>();
        billFilterPopup = new BillFilterPopup(mContext);
        sharedPreferences = getContext().getSharedPreferences("info",MODE_PRIVATE);
        View mStatusBar = view.findViewById(R.id.view_wallet_fillStatusBarView);
        AppBarLayout.LayoutParams lp = (AppBarLayout.LayoutParams) mStatusBar.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = getStatusBarHeight(mContext);
        mStatusBar.setLayoutParams(lp);
        walletPresenter = new WalletPresenter(mContext);
        walletPresenter.onCreate(Constants.BASE_URL);
        walletPresenter.attachView(walletView);
        walletDetailsPresenter = new WalletDetailsPresenter(mContext);
        walletDetailsPresenter.onCreate(Constants.BASE_URL);
        walletDetailsPresenter.attachView(walletDetailsView);
    }

    @Override
    protected void initData() {
        token = sharedPreferences.getString("token","");
       /* rv_wallet.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        // 设置item动画
        rv_wallet.setItemAnimator(new DefaultItemAnimator());*/
        displayMetrics = Utils.getScreenWH(mContext);
        width = displayMetrics.widthPixels;
        height = displayMetrics.heightPixels;
    }

    @Override
    public void initEvents() {
        billFilterPopup.setOnItemClickListener(this);
        tv_wallet_bill_time.setOnClickListener(this);
        tv_wallet_bill_filter.setOnClickListener(this);
        iv_wallet_back.setOnClickListener(this);
        if(!TextUtils.isEmpty(token)){

            walletPresenter.getMineWalletMsg(token);
        }
    }
    private WalletDetailsView walletDetailsView=new WalletDetailsView() {
        @Override
        public void onSuccess(WalletDetails walletDetails) {
            if(walletDetails!=null&&walletDetails.getError()==0){
                LogUtils.d(walletDetails.toString());

                if (walletDetails.getData().getBill() != null && walletDetails.getData().getBill().size() > 0) {
                    rv_wallet.setVisibility(View.VISIBLE);
                    if (detailArrayList != null && detailArrayList.size() > 0) {
                        detailArrayList.clear();
                        detailArrayList.addAll(walletDetails.getData().getBill());

                     /*   if (myWalletBillAdapter != null) {
                            myWalletBillAdapter.notifyDataSetChanged();
                        } else */
                        {
                            myWalletBillAdapter = new MyWalletBillAdapter(mContext, detailArrayList);
                            rv_wallet.setAdapter(myWalletBillAdapter);
                            rv_wallet.setLayoutManager(new LinearLayoutManager(mContext));

                        }
                    }
                } else {
                    rv_wallet.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onError(String result) {

        }
    };
    private WalletView walletView=new WalletView() {
        @Override
        public void onSuccess(Wallet wallet) {
            if(wallet!=null&&wallet.getError()==0){
                LogUtils.d(wallet.toString());
                tv_wallet_balance_details.setText(wallet.getData().getUserinfo().getMoney());
                if(wallet.getData().getDetail()!=null&&wallet.getData().getDetail().size()>0){
                    rv_wallet.setVisibility(View.VISIBLE);
                    detailArrayList.addAll(wallet.getData().getDetail());
                    myWalletBillAdapter = new MyWalletBillAdapter(mContext,detailArrayList);
                    rv_wallet.setAdapter(myWalletBillAdapter);
                    rv_wallet.setLayoutManager(new LinearLayoutManager(mContext));
                }
            }
        }

        @Override
        public void onError(String result) {

        }
    };

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_wallet_back:
                startActivitys(mContext, HomepageActivity.class);
                break;
            case R.id.tv_wallet_bill_filter:
                if(billFilterPopup!=null){
                    if(billFilterPopup.isShowing()){
                        billFilterPopup.dismiss();
                    }
                    billFilterPopup.showAsDropDown(cdl_wallet);
                }
                break;
            case R.id.tv_wallet_bill_time:
                showTimeSelectPopu();
                break;
        }
    }

    @Override
    public void onItemClick(int label) {
        LogUtils.d("label:"+label);
        mLabel=label;
        refreshWalletDetails(token,label+"","1",selectTime);
    }

    private void refreshWalletDetails(String token,String label,String page,String date){

        try {
            date=TextUtils.isEmpty(date)?Utils.longToString(System.currentTimeMillis(),"yyyy-MM"):date;
            label=TextUtils.isEmpty(label)?4+"":label;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        walletDetailsPresenter.getWalletDetailsMsg(token,label,page,date);

    }
    class popuDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            backgroundAlpha(1f);
        }
    }
    public void showTimeSelectPopu() {
        menuView = LayoutInflater.from(mContext).inflate(R.layout.bill_time_select,null);
        mPopupWindow = new PopupWindow(menuView, (int)(width),
                ActionBar.LayoutParams.WRAP_CONTENT);
        wheelTimeSelect = new WheelTimeSelect(menuView, true);
        wheelTimeSelect.screenheight = height;
        String time = DateUtils.currentMonth().toString();
        Calendar calendar = Calendar.getInstance();
        if (JudgeDate.isDate(time, "yyyy-MM-DD")) {
            try {
                calendar.setTime(new Date(time));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        wheelTimeSelect.initDateTimePicker(year, month);
        mPopupWindow.setAnimationStyle(R.style.AnimationPreview);
        mPopupWindow.setTouchable(true);
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable());
        mPopupWindow.showAtLocation(cdl_wallet, Gravity.BOTTOM, 0, 0);
        mPopupWindow.setOnDismissListener(new popuDismissListener());
        backgroundAlpha(0.5f);

        tv_cancel = (TextView) menuView.findViewById(R.id.tv_cancel);
        tv_ensure = (TextView) menuView.findViewById(R.id.tv_ensure);
        tv_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                mPopupWindow.dismiss();
                backgroundAlpha(1f);

            }
        });
        tv_ensure.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                //tv_week_house_time.setText(DateUtils.formateStringH(beginTime,DateUtils.yyyyMMddHHmm));
                mPopupWindow.dismiss();
                backgroundAlpha(1f);
                LogUtils.d(wheelTimeSelect.getTime());
                selectTime = wheelTimeSelect.getTime();
                refreshWalletDetails(token,mLabel+"","1",selectTime);
            }
        });
    }
    public void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgAlpha;
        mActivity.getWindow().setAttributes(lp);
    }
}
