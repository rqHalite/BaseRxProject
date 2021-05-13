package com.rock.basemodel.utils;

import android.content.Context;

import java.io.File;

/**
 * @author: ruan
 * @date: 2020/4/14
 * 设置文件路径
 */
public class AppStoragePath {

    //文件存储路径
    private static String CACHE_PATH = "";

    /**
     * 读取baseurl
     *
     * @param url
     * @return
     */
    public static String getBasUrl(String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }

    public static void setCachePath(String path) {
        AppStoragePath.CACHE_PATH = path;
        File mFile = new File(path);
        if (!mFile.exists()) {
            mFile.mkdirs();
        }
    }

    public static String getCachePath(Context context) {
        String cache = StorageUtils.getCacheDirectory(context) + "/" + MD5Tools.hashKeyForDisk("demo_loadfile");
        File mFile = new File(cache);
        if (!mFile.exists()) {
            mFile.mkdirs();
        }
        return cache;
    }

    public static String getFrescoCachePath(Context context) {
        return context.getExternalCacheDir() + "/" + "imageCache";
    }
}
