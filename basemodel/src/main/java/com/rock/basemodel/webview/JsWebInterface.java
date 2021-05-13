package com.rock.basemodel.webview;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * @author Ruanqi
 * @time 2019/4/8
 */
public class JsWebInterface {

    private Activity activity;
    private MyX5WebView webView;

    public JsWebInterface(Activity context, MyX5WebView x5WebView) {
        this.activity = context;
        this.webView = x5WebView;
    }


    //清空路由历史
    @JavascriptInterface
    public void clearHistory() {
        webView.clearHistory();
    }


}
