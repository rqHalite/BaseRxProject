package com.rock.basemodel;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

import com.hjq.toast.ToastUtils;
import com.rock.basemodel.baseui.utils.ActivityManagement;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.screentools.ScreenAdapterTools;
import com.rock.basemodel.umeng.UmengUtils;
import com.rock.basemodel.utils.FileUtil;
import com.rock.basemodel.webview.X5NetServer;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * @author Ruanqi
 * @time 2019/7/29
 */
public abstract class BaseApplication extends Application {

    public static BaseApplication instance;
//    private MyOkhttp mMyOkHttp;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        ScreenAdapterTools.init(this);

        // 初始化吐司工具类
        ToastUtils.init(this);
        //监听activity状态
        ActivityManagement.getInstance().register(this);
        initWebView();

        //初始化友盟统计
        UmengUtils.init(this);
    }

    private void initWebView() {
//        //搜集本地tbs内核信息并上报服务器，服务器返回结果决定使用哪个内核。
//
//        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
//
//            @Override
//            public void onViewInitFinished(boolean arg0) {
//                // TODO Auto-generated method stub
//                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
//                Log.d("app", " onViewInitFinished is " + arg0);
//            }
//
//            @Override
//            public void onCoreInitFinished() {
//                // TODO Auto-generated method stub
//            }
//        };
//        //x5内核初始化接口
//        QbSdk.initX5Environment(getApplicationContext(),  cb);
        /**
         * 为了防止8.0以上系统
         * IllegalStateException: Not allowed to start service Intent
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForegroundService(new Intent(instance, X5NetServer.class));
        } else {
            startService(new Intent(instance, X5NetServer.class));
        }
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ScreenAdapterTools.getInstance().reset(instance);
    }

    public static BaseApplication getInstance() {
        return instance;
    }

    public void showToast(Activity view, String content, @ToastUtil.ToastType int toast_type) {
        //app在前台的时候在现实toast
        if (ActivityManagement.getInstance().getActivityCount() != 0) {
            ToastUtils.show(content);
        }
    }

    //webview缓存路径
    public String getWebViewCachePath() {
        return FileUtil.getDiskCachePath(this) + "/cache/";
    }



}
