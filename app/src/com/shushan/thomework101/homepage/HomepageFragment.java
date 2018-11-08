package com.shushan.thomework101.homepage;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shushan.thomework101.Constants;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.Homepage;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageData;
import com.shushan.thomework101.HttpHelper.service.entity.homepage.HomepageTutorship;
import com.shushan.thomework101.HttpHelper.service.entity.register.Online;
import com.shushan.thomework101.HttpHelper.service.presenter.HomepagePresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.HomepageTutorshipPresenter;
import com.shushan.thomework101.HttpHelper.service.presenter.OnlinePresenter;
import com.shushan.thomework101.HttpHelper.service.view.HomepageTutorshipView;
import com.shushan.thomework101.HttpHelper.service.view.HomepageView;
import com.shushan.thomework101.HttpHelper.service.view.OnlineView;
import com.shushan.thomework101.R;
import com.shushan.thomework101.adapter.HomepageListViewAdapter;
import com.shushan.thomework101.banner.BannerView;
import com.shushan.thomework101.banner.HolderCreator;
import com.shushan.thomework101.banner.ImageViewRoundRect;
import com.shushan.thomework101.banner.ViewHolder;
import com.shushan.thomework101.base.BaseFragment;
import com.shushan.thomework101.bean.HomeworkOrdersList;
import com.shushan.thomework101.utils.LogUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;

import static android.content.Context.MODE_PRIVATE;

public class HomepageFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.iv_home_mine)
    ImageView iv_home_mine;
    @Bind(R.id.iv_home_news)
    ImageView iv_home_news;
    @Bind(R.id.btn_start_tutorship)
    Button btn_start_tutorship;
    @Bind(R.id.tv_home_title)
    TextView tv_home_title;
    @Bind(R.id.iv_bottom_bg)
    ImageView iv_bottom_bg;
    @Bind(R.id.rl_homepage_bg)
    RelativeLayout rl_homepage_bg;
    @Bind(R.id.tv_homepage_check)
    TextView tv_homepage_check;
    @Bind(R.id.iv_homepage_check)
    ImageView iv_homepage_check;
    @Bind(R.id.tv_homepage_tutorship)
    TextView tv_homepage_tutorship;
    @Bind(R.id.iv_homepage_tutorship)
    ImageView iv_homepage_tutorship;
    @Bind(R.id.lv_homepage)
    ListView lv_homepage;
    @Bind(R.id.btn_homepage_connect_switch)
    Button btn_homepage_connect_switch;
    private HomeMineClickInterface homeMineClickInterface;
    public static final int []BANNER = new int[]{R.drawable.banner1,R.drawable.banner2,R.drawable.banner3,R.drawable.banner4,R.drawable.banner5};
    private BannerView mBanner;
    private ArrayList<HomeworkOrdersList> homeworkOrdersLists;
    private ArrayList<HomeworkOrdersList> homeworkHelpOrdersLists;
    private HomeworkOrdersList homeworkOrdersList;
    private OnlinePresenter onlinePresenter;
    private int tid;
    private String token;
    private int online;
    private HomepagePresenter homepagePresenter;
    private Map<String, String> requestMap;
    private ArrayList<HomepageData> checkHomeworkDataList;
    private ArrayList<HomepageData> haveOrderTutorshipDataList;
    private ArrayList<HomepageData> bookTutorshipDataList;
    private ArrayList<HomepageData> realTimetTutorshipDataList;
    private int type=0;//0作业检查 12(作业辅导未接单预约)13(作业辅导未接单实时) 2(作业辅导已接单) 22(作业辅导已接单预约)23作业辅导(作业辅导已接单实时)
    private HomepageTutorshipPresenter homepageTutorshipPresenter;
    private SharedPreferences sharedPreferences;

    public HomepageFragment() {

    }

    @Override
    protected int getContentViewId() {
        return R.layout.fragment_homepage;
    }

    @Override
    public void initViews(View view) {
        onlinePresenter = new OnlinePresenter(getContext());
        onlinePresenter.onCreate(Constants.BASE_URL);
        onlinePresenter.attachView(onlineView);
        homepagePresenter = new HomepagePresenter(getContext());
        homepagePresenter.onCreate(Constants.BASE_URL);
        homepagePresenter.attachView(homepageView);
        homepageTutorshipPresenter = new HomepageTutorshipPresenter(getContext());
        homepageTutorshipPresenter.onCreate(Constants.BASE_URL);
        homepageTutorshipPresenter.attachView(homepageTutorshipView);
        mBanner = (BannerView) view.findViewById(R.id.banner);
        mBanner.setBannerPageClickListener(new BannerView.BannerPageClickListener() {
            @Override
            public void onPageClick(View view, int position) {
                //Toast.makeText(getContext(),"click page:"+position,Toast.LENGTH_LONG).show();
            }
        });
        mBanner.addPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //Log.e(TAG,"----->addPageChangeLisnter:"+position + "positionOffset:"+positionOffset+ "positionOffsetPixels:"+positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                //Log.e(TAG,"addPageChangeLisnter:"+position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        List<Integer> bannerList = new ArrayList<>();
        for(int i=0;i<BANNER.length;i++){
            bannerList.add(BANNER[i]);
        }
        mBanner.setIndicatorVisible(true);
        // 代码中更改indicator 的位置
        //mMZBanner.setIndicatorAlign(MZBannerView.IndicatorAlign.LEFT);
        //mMZBanner.setIndicatorPadding(10,0,0,150);
        mBanner.setPages(bannerList, new HolderCreator<BannerViewHolder>() {
            @Override
            public BannerViewHolder createViewHolder() {
                return new BannerViewHolder();
            }
        });

    }

    @Override
    protected void initData() {
        requestMap = new HashMap<>();
        checkHomeworkDataList=new ArrayList<>();
        haveOrderTutorshipDataList=new ArrayList<>();
        bookTutorshipDataList=new ArrayList<>();
        realTimetTutorshipDataList=new ArrayList<>();
        sharedPreferences = getContext().getSharedPreferences("info",MODE_PRIVATE);
        tid = sharedPreferences.getInt("tid",0);
        token = sharedPreferences.getString("token","");
        online = sharedPreferences.getInt("online",0);
        homeworkOrdersLists = new ArrayList<>();
        homeworkOrdersList = new HomeworkOrdersList();
        homeworkHelpOrdersLists=new ArrayList<>();

    }

    private HomepageTutorshipView homepageTutorshipView = new HomepageTutorshipView() {
        @Override
        public void onSuccess(HomepageTutorship homepageTutorship) {
            if (homepageTutorship != null && homepageTutorship.getError() == 0) {
                LogUtils.d(homepageTutorship.toString());
                if (homepageTutorship.getData().getReceive() != null && homepageTutorship.getData().getReceive().size() > 0) {

                    for (int i = 0; i < homepageTutorship.getData().getReceive().size(); i++) {

                        if (homepageTutorship.getData().getReceive().get(i).getIs_receive() == 2) {
                            type = 2;
                            haveOrderTutorshipDataList.add(homepageTutorship.getData().getReceive().get(i));

                        }
                    }
                    homeworkOrdersList = new HomeworkOrdersList();
                    homeworkOrdersList.setType(type);
                    homeworkOrdersList.setHomepageData(haveOrderTutorshipDataList);
                    homeworkHelpOrdersLists.add(homeworkOrdersList);
                }
                if (homepageTutorship.getData().getGuide_pre() != null && homepageTutorship.getData().getGuide_pre().size() > 0) {

                    for (int i = 0; i < homepageTutorship.getData().getGuide_pre().size(); i++) {

                        if (homepageTutorship.getData().getGuide_pre().get(i).getLabel() == 2) {
                            type = 12;
                            bookTutorshipDataList.add(homepageTutorship.getData().getGuide_pre().get(i));

                        }
                    }

                    homeworkOrdersList = new HomeworkOrdersList();
                    homeworkOrdersList.setType(type);
                    homeworkOrdersList.setHomepageData(bookTutorshipDataList);
                    homeworkHelpOrdersLists.add(homeworkOrdersList);

                }

                if (homepageTutorship.getData().getGuide_now() != null && homepageTutorship.getData().getGuide_now().size() > 0) {

                    for (int i = 0; i < homepageTutorship.getData().getGuide_now().size(); i++) {

                        if (homepageTutorship.getData().getGuide_now().get(i).getLabel() == 3) {
                            type = 13;
                            realTimetTutorshipDataList.add(homepageTutorship.getData().getGuide_now().get(i));
                        }
                    }

                    homeworkOrdersList = new HomeworkOrdersList();
                    homeworkOrdersList.setType(type);
                    homeworkOrdersList.setHomepageData(realTimetTutorshipDataList);
                    homeworkHelpOrdersLists.add(homeworkOrdersList);

                }

                if (homeworkHelpOrdersLists != null && homeworkHelpOrdersLists.size() > 0) {
                    lv_homepage.setVisibility(View.VISIBLE);
                    HomepageListViewAdapter homepageListViewAdapter = new HomepageListViewAdapter(homeworkHelpOrdersLists, getContext());
                    lv_homepage.setAdapter(homepageListViewAdapter);
                } else {
                    lv_homepage.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void onError(String result) {

        }
    };

    private HomepageView homepageView = new HomepageView() {
        @Override
        public void onSuccess(Homepage homepage) {

            if (homepage != null && homepage.getError() == 0) {
                LogUtils.d(homepage.toString());

                if (homepage.getData() != null && homepage.getData().size() > 0) {

                    for (int i = 0; i < homepage.getData().size(); i++) {

                        if (homepage.getData().get(i).getLabel() == 1) {
                            type = 0;
                            if(checkHomeworkDataList!=null&&checkHomeworkDataList.size()>0){
                                checkHomeworkDataList.clear();
                            }
                            checkHomeworkDataList.add(homepage.getData().get(i));
                        }
                    }
                    homeworkOrdersList.setType(type);
                    homeworkOrdersList.setHomepageData(checkHomeworkDataList);

                    if(homeworkOrdersLists!=null&&homeworkOrdersLists.size()>0){
                        homeworkOrdersLists.clear();
                    }
                    homeworkOrdersLists.add(homeworkOrdersList);
                    if (homeworkOrdersLists != null && homeworkOrdersLists.size() > 0) {
                        HomepageListViewAdapter homepageListViewAdapter = new HomepageListViewAdapter(homeworkOrdersLists, getContext());
                        lv_homepage.setAdapter(homepageListViewAdapter);
                    }
                }
            }
        }


        @Override
        public void onError(String result) {

        }
    };
    private OnlineView onlineView=new OnlineView() {
        @Override
        public void onSuccess(Online online) {
            if(online!=null&&online.getError()==0){
                sharedPreferences.edit().putInt("online",1).commit();
                tv_home_title.setVisibility(View.INVISIBLE);
                iv_bottom_bg.setVisibility(View.INVISIBLE);
                mBanner.setVisibility(View.INVISIBLE);
                rl_homepage_bg.setBackgroundResource(R.color.white);
                changeStatusBarTextImgColor(true,getActivity());
                iv_home_mine.setBackgroundResource(R.drawable.home_my_gray);
                iv_home_news.setBackgroundResource(R.drawable.home_news_gray);
                tv_homepage_check.setVisibility(View.VISIBLE);
                tv_homepage_check.setTextColor(getResources().getColor(R.color.identity_rb_font));
                iv_homepage_check.setVisibility(View.VISIBLE);
                tv_homepage_tutorship.setVisibility(View.VISIBLE);
                tv_homepage_tutorship.setTextColor(getResources().getColor(R.color.black));
                iv_homepage_tutorship.setVisibility(View.INVISIBLE);
                lv_homepage.setVisibility(View.VISIBLE);
                btn_homepage_connect_switch.setVisibility(View.VISIBLE);
                btn_start_tutorship.setVisibility(View.INVISIBLE);

                if(!TextUtils.isEmpty(token)){
                    requestMap.put("tid",tid+"");
                    requestMap.put("token", token);
                    requestMap.put("page",0+"");
                    requestMap.put("label",1+"");

                    LogUtils.d("tid:" + tid +
                            "\n" + "token:" + token );
                    homepagePresenter.getHomepageMsg(requestMap);
                }


            }
        }

        @Override
        public void onError(String result) {

        }
    };
    @Override
    public void initEvents() {
        iv_home_mine.setOnClickListener(this);
        btn_start_tutorship.setOnClickListener(this);
        tv_homepage_check.setOnClickListener(this);
        tv_homepage_tutorship.setOnClickListener(this);
        btn_homepage_connect_switch.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_home_mine:
                if(homeMineClickInterface!=null){
                    homeMineClickInterface.mineClick();
                }
                break;
            case R.id.btn_start_tutorship:

                if(!TextUtils.isEmpty(token)){

                    LogUtils.d("tid:" + tid + "\n" + "token:" + token + "\n" + "online:" + online);
                    onlinePresenter.getOnlineMsg(tid+"",token,online+"");
                }
                break;
            case R.id.tv_homepage_check:
                tv_homepage_check.setVisibility(View.VISIBLE);
                iv_homepage_check.setVisibility(View.VISIBLE);
                tv_homepage_tutorship.setVisibility(View.VISIBLE);
                iv_homepage_tutorship.setVisibility(View.INVISIBLE);
                tv_homepage_check.setTextColor(getResources().getColor(R.color.identity_rb_font));
                tv_homepage_tutorship.setTextColor(getResources().getColor(R.color.black));

                if(homeworkOrdersLists!=null&&homeworkOrdersLists.size()>0){
                    lv_homepage.setVisibility(View.VISIBLE);
                    HomepageListViewAdapter homepageListViewAdapter=new HomepageListViewAdapter(homeworkOrdersLists,getContext());
                    lv_homepage.setAdapter(homepageListViewAdapter);
                }else{
                    lv_homepage.setVisibility(View.GONE);
                }

                break;
            case R.id.tv_homepage_tutorship:

                if(haveOrderTutorshipDataList!=null&&haveOrderTutorshipDataList.size()>0){
                    haveOrderTutorshipDataList.clear();
                }
                if(bookTutorshipDataList!=null&&bookTutorshipDataList.size()>0){
                    bookTutorshipDataList.clear();
                }
                if(realTimetTutorshipDataList!=null&&realTimetTutorshipDataList.size()>0){
                    realTimetTutorshipDataList.clear();
                }
                if(homeworkHelpOrdersLists!=null&&homeworkHelpOrdersLists.size()>0){
                    homeworkHelpOrdersLists.clear();
                 }

                tv_homepage_check.setVisibility(View.VISIBLE);
                iv_homepage_check.setVisibility(View.INVISIBLE);
                tv_homepage_tutorship.setVisibility(View.VISIBLE);
                iv_homepage_tutorship.setVisibility(View.VISIBLE);
                tv_homepage_check.setTextColor(getResources().getColor(R.color.black));
                tv_homepage_tutorship.setTextColor(getResources().getColor(R.color.identity_rb_font));

                if(!TextUtils.isEmpty(token)){
                    requestMap.put("tid",tid+"");
                    requestMap.put("token", token);
                    requestMap.put("page",0+"");
                    requestMap.put("label",2+"");

                    LogUtils.d("tid:" + tid +
                            "\n" + "token:" + token );
                    homepageTutorshipPresenter.getHomepageMsg(requestMap);
                }
                break;
        }

    }
    public void setHomeMineClickInterface(HomeMineClickInterface homeMineClickInterface){
        this.homeMineClickInterface=homeMineClickInterface;
    }

    public static class BannerViewHolder implements ViewHolder<Integer> {
        private ImageViewRoundRect mImageView;
        @Override
        public View createView(Context context) {
            // 返回页面布局文件
            View view = LayoutInflater.from(context).inflate(R.layout.banner_item,null);
            mImageView = (ImageViewRoundRect) view.findViewById(R.id.banner_image);
            mImageView.setType(ImageViewRoundRect.TYPE_ROUND);
            mImageView.setRoundRadius((int)context.getResources().getDimension(R.dimen.x20));
            return view;
        }

        @Override
        public void onBind(Context context, int position, Integer data) {
            // 数据绑定
            mImageView.setImageResource(data);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
        mBanner.pause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBanner.start();
        online = sharedPreferences.getInt("online", 0);
        if (online == 1) {
            tv_home_title.setVisibility(View.INVISIBLE);
            iv_bottom_bg.setVisibility(View.INVISIBLE);
            mBanner.setVisibility(View.INVISIBLE);
            rl_homepage_bg.setBackgroundResource(R.color.white);
            changeStatusBarTextImgColor(true, getActivity());
            iv_home_mine.setBackgroundResource(R.drawable.home_my_gray);
            iv_home_news.setBackgroundResource(R.drawable.home_news_gray);
            tv_homepage_check.setVisibility(View.VISIBLE);
            tv_homepage_check.setTextColor(getResources().getColor(R.color.identity_rb_font));
            iv_homepage_check.setVisibility(View.VISIBLE);
            tv_homepage_tutorship.setVisibility(View.VISIBLE);
            tv_homepage_tutorship.setTextColor(getResources().getColor(R.color.black));
            iv_homepage_tutorship.setVisibility(View.INVISIBLE);
            lv_homepage.setVisibility(View.VISIBLE);
            btn_homepage_connect_switch.setVisibility(View.VISIBLE);
            btn_start_tutorship.setVisibility(View.INVISIBLE);

            if (!TextUtils.isEmpty(token)) {
                requestMap.put("tid", tid + "");
                requestMap.put("token", token);
                requestMap.put("page", 0 + "");
                requestMap.put("label", 1 + "");

                LogUtils.d("tid:" + tid +
                        "\n" + "token:" + token);
                homepagePresenter.getHomepageMsg(requestMap);
            }
        }
    }

}
