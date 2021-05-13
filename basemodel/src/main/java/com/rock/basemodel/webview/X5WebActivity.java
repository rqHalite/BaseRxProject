package com.rock.basemodel.webview;

import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rock.basemodel.R;
import com.rock.basemodel.baseui.ui.BasicTitleActivity;


public class X5WebActivity extends BasicTitleActivity {
    public static final String WEB_URL = "web_url";
    private MyX5WebView mWebView;
    private Context mContent;
    private TextView errorText;
    private LinearLayout errorView;
    private String mIntentUrl;

    @Override
    protected int onCreateLayout() {
        return R.layout.activity_x5_web;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        hideToolbarView();
        mContent = this;
        mWebView = findViewById(R.id.webview);
        errorText = findViewById(R.id.error_text);
        errorView = findViewById(R.id.error_view);
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initX5();
        if (savedInstanceState != null) {
            mWebView.restoreState(savedInstanceState);
            mIntentUrl = savedInstanceState.getString(WEB_URL);
        } else {
            mIntentUrl = getIntent().getExtras().getString(WEB_URL);
            mWebView.loadUrl(mIntentUrl);
        }
        Log.i("url", "url 》》》》》》》" + mIntentUrl);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mWebView.saveState(outState);
        outState.putString(WEB_URL, mWebView.getUrl());
    }

    private void initX5() {
        mWebView.setErrorView(errorView, errorText);
        mWebView.getSettings().setBlockNetworkImage(true);//将图片下载阻塞
    }


    /**
     * 返回键监听
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 监听到返回按钮点击事件
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView != null && mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            } else {
                return super.onKeyDown(keyCode, event);
            }
        }
        return super.onKeyDown(keyCode, event);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.setVisibility(View.GONE);
            mWebView.destroy();
        }
    }


}
