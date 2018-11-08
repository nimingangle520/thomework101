package com.shushan.thomework101;

import android.app.Application;
import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.shushan.thomework101.third.AuthPreferences;
import com.shushan.thomework101.third.DemoCache;
import com.shushan.thomework101.third.ScreenUtil;
import com.shushan.thomework101.third.UserPreferences;

public class MainApplication extends Application {
    protected static MainApplication mainApplication = null;
    /** 上下文 */
    protected Context mContext          = null;
    /** Activity 栈 */
    public ActivityStack mActivityStack = null;

    @Override
    public void onCreate() {
        super.onCreate();
        // 由于Application类本身已经单例，所以直接按以下处理即可
        mainApplication = this;
        mContext = getApplicationContext();     // 获取上下文
        CrashHandler catchHandler = CrashHandler.getInstance();
        catchHandler.init(getApplicationContext());
        initConfiguration();
        DemoCache.setContext(this);
        ScreenUtil.init(this);
        NIMClient.init(this, getLoginInfo(), getOptions());

    }

    /**
     * 获取当前类实例对象
     * @return
     */
    public static MainApplication getInstance(){
        return mainApplication;
    }

    /**
     * @description 初始化配置文件
     *
     * @return void
     */
    private void initConfiguration() {

    }

    private LoginInfo getLoginInfo() {
        String account = AuthPreferences.getUserAccount();
        String token = AuthPreferences.getUserToken();

        if (!TextUtils.isEmpty(account) && !TextUtils.isEmpty(token)) {
            DemoCache.setAccount(account.toLowerCase());
            return new LoginInfo(account, token);
        } else {
            return null;
        }
    }

    private SDKOptions getOptions() {
        SDKOptions options = new SDKOptions();
        // 如果将新消息通知提醒托管给SDK完成，需要添加以下配置。
        StatusBarNotificationConfig config = UserPreferences.getStatusConfig();
        if (config == null) {
            config = new StatusBarNotificationConfig();
        }
        // 点击通知需要跳转到的界面
        config.notificationEntrance = MainActivity.class;
        config.notificationSmallIconId = R.drawable.app;

        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
        options.statusBarNotificationConfig = config;
        UserPreferences.setStatusConfig(config);

        // 配置保存图片，文件，log等数据的目录
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/nim/";
        options.sdkStorageRootPath = sdkPath;
        //Log.i("demo", FlavorDependent.getInstance().getFlavorName() + " demo nim sdk log path=" + sdkPath);

        // 配置数据库加密秘钥
        options.databaseEncryptKey = "NETEASE";

        // 配置是否需要预下载附件缩略图
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小，
        options.thumbnailSize = (int) (0.5 * ScreenUtil.screenWidth);

        // 用户信息提供者
        options.userInfoProvider = null;

        // 定制通知栏提醒文案（可选，如果不定制将采用SDK默认文案）
        options.messageNotifierCustomization = null;

        return options;
    }

}
