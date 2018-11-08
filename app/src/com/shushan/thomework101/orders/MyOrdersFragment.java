package com.shushan.thomework101.orders;

import android.app.Activity;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.MyOrdersFragmentAdapter;
import com.shushan.thomework101.base.BaseFragment;
import com.shushan.thomework101.homepage.HomepageActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

import static android.content.Context.MODE_PRIVATE;

public class MyOrdersFragment extends BaseFragment implements View.OnClickListener {

    @Bind(R.id.ll_orders_homework_check)
    LinearLayout ll_orders_homework_check;
    @Bind(R.id.ll_orders_homework_tutorship)
    LinearLayout ll_orders_homework_tutorship;
    @Bind(R.id.view_homework_check)
    View view_homework_check;
    @Bind(R.id.view_homework_tutorship)
    View view_homework_tutorship;
    @Bind(R.id.tv_orders_homework_check)
    TextView tv_orders_homework_check;
    @Bind(R.id.tv_orders_homework_tutorship)
    TextView tv_orders_homework_tutorship;
    @Bind(R.id.rl_orders_top)
    RelativeLayout rl_orders_top;
    @Bind(R.id.iv_orders_back)
    ImageView iv_orders_back;
    @Bind(R.id.tb_orders)
    TabLayout tb_orders;
    @Bind(R.id.vp_orders)
    ViewPager vp_orders;
    private String [] orders_category;
    private BackClickInterface backClickInterface;
    private List<Fragment> orders_fragment;
    private WholeFragment wholeFragment;
    private ObligationFragment obligationFragment;
    private CompleteFragment completeFragment;
    private CanceledFragment canceledFragment;
    private MyOrdersFragmentAdapter myOrdersFragmentAdapter;
    private int mine_order_label=1;
    private SharedPreferences sharedPreferences;

    public MyOrdersFragment() {
        // Required empty public constructor

    }

    @Override
    protected int getContentViewId() {

        changeStatusBarTextImgColor(true, (Activity) mContext);
        return R.layout.fragment_my_orders;
    }

    @Override
    public void initViews(View view) {
        sharedPreferences = mContext.getSharedPreferences("info",MODE_PRIVATE);
        sharedPreferences.edit().putInt("mine_order_label",mine_order_label).commit();
        View mStatusBar = view.findViewById(R.id.fillStatusBarView);
        RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) mStatusBar.getLayoutParams();
        lp.width = RelativeLayout.LayoutParams.MATCH_PARENT;
        lp.height = getStatusBarHeight(mContext);
        mStatusBar.setLayoutParams(lp);
    }

    @Override
    protected void initData() {
        orders_category=getResources().getStringArray(R.array.orders_category);
        orders_fragment=new ArrayList<>();
        wholeFragment = new WholeFragment();
        obligationFragment = new ObligationFragment();
        completeFragment = new CompleteFragment();
        canceledFragment = new CanceledFragment();
        orders_fragment.add(wholeFragment);
        orders_fragment.add(obligationFragment);
        orders_fragment.add(completeFragment);
        orders_fragment.add(canceledFragment);
        tb_orders.addTab(tb_orders.newTab().setText(orders_category[0]));
        tb_orders.addTab(tb_orders.newTab().setText(orders_category[1]));
        tb_orders.addTab(tb_orders.newTab().setText(orders_category[2]));
        tb_orders.addTab(tb_orders.newTab().setText(orders_category[3]));
    }

    @Override
    public void initEvents() {
        ll_orders_homework_check.setOnClickListener(this);
        ll_orders_homework_tutorship.setOnClickListener(this);
        iv_orders_back.setOnClickListener(this);
        tb_orders.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        myOrdersFragmentAdapter = new MyOrdersFragmentAdapter(getChildFragmentManager(), Arrays.asList(orders_category),orders_fragment);
        vp_orders.setAdapter(myOrdersFragmentAdapter);
        vp_orders.setOffscreenPageLimit(orders_category.length);
        tb_orders.setupWithViewPager(vp_orders);
        vp_orders.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    public void setBackClickInterface(BackClickInterface backClickInterface) {
        this.backClickInterface = backClickInterface;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_orders_back:
                /*if(backClickInterface!=null){
                    backClickInterface.backClick();
                }*/
                startActivitys(mContext, HomepageActivity.class);
                break;
            case R.id.ll_orders_homework_check:
                mine_order_label=1;
                view_homework_check.setVisibility(View.VISIBLE);
                tv_orders_homework_check.setTextColor(getResources().getColor(R.color.identity_rb_font));
                view_homework_tutorship.setVisibility(View.INVISIBLE);
                tv_orders_homework_tutorship.setTextColor(getResources().getColor(R.color.black));

                sharedPreferences.edit().putInt("mine_order_label",mine_order_label).commit();
                myOrdersFragmentAdapter = new MyOrdersFragmentAdapter(getChildFragmentManager(), Arrays.asList(orders_category),orders_fragment);
                vp_orders.setAdapter(myOrdersFragmentAdapter);
                vp_orders.setOffscreenPageLimit(orders_category.length);
                tb_orders.setupWithViewPager(vp_orders);

                break;
            case R.id.ll_orders_homework_tutorship:
                mine_order_label=2;
                view_homework_check.setVisibility(View.INVISIBLE);
                tv_orders_homework_check.setTextColor(getResources().getColor(R.color.black));
                view_homework_tutorship.setVisibility(View.VISIBLE);
                tv_orders_homework_tutorship.setTextColor(getResources().getColor(R.color.identity_rb_font));

                sharedPreferences.edit().putInt("mine_order_label",mine_order_label).commit();
                myOrdersFragmentAdapter = new MyOrdersFragmentAdapter(getChildFragmentManager(), Arrays.asList(orders_category),orders_fragment);
                vp_orders.setAdapter(myOrdersFragmentAdapter);
                vp_orders.setOffscreenPageLimit(orders_category.length);
                tb_orders.setupWithViewPager(vp_orders);
                break;

        }
    }

}
