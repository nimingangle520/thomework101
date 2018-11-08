package com.shushan.thomework101.homepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.HomepageMenusAdapter;
import com.shushan.thomework101.bean.HomepageMenusItem;
import com.shushan.thomework101.orders.BackClickInterface;
import com.shushan.thomework101.orders.MyOrdersFragment;
import com.shushan.thomework101.wallet.MyWalletFragment;

import java.util.ArrayList;

public class HomepageActivity extends FragmentActivity implements AdapterView.OnItemClickListener, HomeMineClickInterface,BackClickInterface,View.OnClickListener{

    private DrawerLayout drawer_layout;
    private ListView lv_homepage_left;
    private FrameLayout fl_container;
    private int[] menusIconId = new int[]{R.drawable.my_orders, R.drawable.my_wallet, R.drawable.my_service, R.drawable.my_invite, R.drawable.my_setup};
    private String[] menusTitles;
    private ArrayList<HomepageMenusItem> homepageMenusItems;
    private HomepageMenusAdapter homepageMenusAdapter;
    private HomepageFragment homepageFragment;
    private LinearLayout ll_homepage_lef;
    private MyOrdersFragment ordersFragment;
    private MyWalletFragment myWalletFragment;
    private int tid;
    private String token;
    private int online;
    private SharedPreferences sharedPreferences;
    private RelativeLayout rl_homepage_info;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        initViews();
        initEvents();
        initData();
        setDefaultFragment();
    }

    protected void initViews() {
        drawer_layout = (DrawerLayout) findViewById(R.id.drawer_layout);
        lv_homepage_left = (ListView) findViewById(R.id.lv_homepage_left);
        fl_container = (FrameLayout) findViewById(R.id.fl_container);
        ll_homepage_lef = (LinearLayout) findViewById(R.id.ll_homepage_left);
        rl_homepage_info=findViewById(R.id.rl_homepage_info);

    }

    protected void initEvents() {
        rl_homepage_info.setOnClickListener(this);
        homepageMenusItems = new ArrayList<>();
        drawer_layout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {

            }

            @Override
            public void onDrawerClosed(@NonNull View view) {

            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }

    protected void initData() {
        sharedPreferences = getSharedPreferences("info",MODE_PRIVATE);
        menusTitles = getResources().getStringArray(R.array.menus_titles);
        for (int i = 0; i < menusIconId.length; i++) {
            homepageMenusItems.add(new HomepageMenusItem(menusIconId[i], menusTitles[i]));
        }
        homepageMenusAdapter = new HomepageMenusAdapter(this, homepageMenusItems);
        lv_homepage_left.setAdapter(homepageMenusAdapter);
        lv_homepage_left.setOnItemClickListener(this);
    }

    private void setDefaultFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        homepageFragment = new HomepageFragment();
        homepageFragment.setHomeMineClickInterface(this);
        transaction.replace(R.id.fl_container, homepageFragment).commit();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (position) {
            case 0:
                if (ordersFragment == null) {
                    ordersFragment = new MyOrdersFragment();
                }
                ordersFragment.setBackClickInterface(this);
                transaction.replace(R.id.fl_container, ordersFragment);
                break;
            case 1:
                if (myWalletFragment == null) {
                    myWalletFragment = new MyWalletFragment();
                }
                transaction.replace(R.id.fl_container, myWalletFragment);
                break;
            /*case 2:
                if (fragmentMine == null) {
                    fragmentMine = new FragmentMine();
                }
                transaction.replace(R.id.ll_content, fragmentMine);
                break;
            default:
                if (homepageFragment == null) {
                    homepageFragment= new HomepageFragment();
                }
                transaction.replace(R.id.ll_content, homepageFragment);
                break;*/
        }
        transaction.commit();
        drawer_layout.closeDrawers();
    }

    @Override
    public void mineClick() {
        drawer_layout.openDrawer(ll_homepage_lef);
    }

    @Override
    public void backClick() {
        //drawer_layout.openDrawer(ll_homepage_lef)

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.rl_homepage_info:
                startActivity(new Intent(this,InfoActivity.class));
                break;
        }
    }
}