package com.rock.basemodel.webview;

import android.content.Context;
import android.content.res.Resources;

import com.rock.basemodel.R;


/**
 * @author Ruanqi
 * @time 2019/2/28
 */
public class ADFilterTool {

    /**
     * 屏蔽广告的NoAdWebViewClient类
     *
     * @param context
     * @param url
     * @return true 为广告链接，false 为正常连接
     */
    public static boolean hasAd(Context context, String url) {
        Resources res = context.getResources();
        String[] adUrls = res.getStringArray(R.array.adBlockUrl);
        for (String adUrl : adUrls) {
            if (url.contains(adUrl)) {
                return true;
            }
        }
        return false;
    }
}
