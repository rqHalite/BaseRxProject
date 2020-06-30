package com.rock.basemodel.umeng;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;


/**
 * @author: ruan
 * @date: 2020/4/22
 */
public class UmengUtils {
    /**
     * 初始化友盟相关 SDK
     */
    public static void init(Application application) {

        try {
            Bundle metaData = application.getPackageManager().getApplicationInfo(application.getPackageName(), PackageManager.GET_META_DATA).metaData;
            // 友盟统计，API 说明：https://developer.umeng.com/docs/66632/detail/101814#h1-u521Du59CBu5316u53CAu901Au7528u63A5u53E32
            UMConfigure.init(application, String.valueOf(metaData.get("UMENG_APPKEY")),"umeng", UMConfigure.DEVICE_TYPE_PHONE,"");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Activity 统计
     */
    public static void onResume(Activity activity) {
        // 手动统计页面
        MobclickAgent.onPageStart(activity.getTitle().toString());
        // 友盟统计
        MobclickAgent.onResume(activity);
    }

    /**
     * Activity 统计
     */
    public static void onPause(Activity activity) {
        // 手动统计页面，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据
        MobclickAgent.onPageStart(activity.getTitle().toString());
        // 友盟统计
        MobclickAgent.onPause(activity);
    }

    /**
     * Fragment 统计
     */
    public static void onResume(Fragment fragment) {
        // 友盟统计
        MobclickAgent.onResume(fragment.getContext());
    }

    /**
     * Fragment 统计
     */
    public static void onPause(Fragment fragment) {
        // 友盟统计
        MobclickAgent.onPause(fragment.getContext());
    }




    /**
     * 判断 App 是否安装
     */

    private static boolean isAppInstalled(Context context, final String packageName) {
        try {
            context.getPackageManager().getApplicationInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException ignored) {
            return false;
        }
    }
}
