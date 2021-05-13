package com.rock.basemodel.baseui.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * @author Ruanqi
 * @time 2019/3/19
 * 用来存储个人信息
 */
public class UserUtils {

    private Context mContext = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    private static UserUtils mInstance = null;

    public UserUtils(Context context) {
        mContext = context;
        mSharedPreferences = context.getSharedPreferences(
                "user", Context.MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();
    }


    public static UserUtils getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserUtils(context);
        }
        return mInstance;
    }


    /**
     * 保存key
     *
     * @param key
     * @param value
     */
    public void saveValue(String key, String value) {
        mEditor.putString(key, value);
        mEditor.commit();
    }

    /**
     * 传入KEY，得到对应的数据。
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        String retValue = mSharedPreferences.getString(key, "");
        return retValue;
    }

    public void removeValue(String key) {
        mEditor.putString(key, null);
        mEditor.commit();
    }

    /**
     * 传入KEY，得到对应的数据。
     *
     * @param key
     * @return
     */
    public boolean getBooleanValue(String key) {
        boolean retValue = mSharedPreferences.getBoolean(key, false);
        return retValue;
    }

    /**
     * 存储boolean value
     *
     * @param key
     * @param value
     */
    public void saveBooleanValue(String key, boolean value) {
        mEditor.putBoolean(key, value);
        mEditor.commit();
    }

    /**
     * 储存int value
     *
     * @param key
     * @param value
     */
    public void saveIntValue(String key, int value) {
        mEditor.putInt(key, value);
        mEditor.commit();
    }

    /**
     * 获取int value
     *
     * @return
     */
    public int getIntVaule(String key) {
        int retValue = mSharedPreferences.getInt(key, -1);
        return retValue;
    }

    /**
     * 获取int value
     *
     * @return
     */
    public long getLongVaule(String key) {
        long retValue = mSharedPreferences.getLong(key, 0);
        return retValue;
    }

    /**
     * 置空shareperfence
     */
    public void deleteFile() {
        mEditor.clear();
        mEditor.commit();
    }

    /**
     * get App versionName
     * 版本名
     *
     * @param context
     * @return
     */

    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packageInfo;
        String versionName = "";
        try {
            packageInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
