package com.rock.basemodel.baseui.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.StringRes;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.screentools.ScreenAdapterTools;


/**
 * created by zhud on 2018/10/25
 */
public abstract class BasicPopupWindow extends PopupWindow {
    private View mView;
    protected Context mContext;
    protected Bundle mBundle;
    private Activity activity;

    public BasicPopupWindow(Context context) {
        this(0, context);
    }

    public BasicPopupWindow(int layoutId, Context context) {
        this.mContext = context;
        activity = (Activity) context;
        this.mBundle = new Bundle();
        if (layoutId != 0)
            mView = LayoutInflater.from(context).inflate(layoutId, null, false);
        else mView = LayoutInflater.from(context).inflate(onCreateLayout(), null, false);
        ScreenAdapterTools.getInstance().loadView(mView);
        setContentView(mView);
        setAnimationStyle(-1);
        //设置可以获取焦点
        setFocusable(true);
        //设置可以触摸弹出框以外的区域
        setOutsideTouchable(true);
        setBackgroundDrawable(new PaintDrawable(0x00000000));
        // 设置PopupWindow弹出窗体的宽
        setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置PopupWindow弹出窗体的高
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
    }

    protected abstract int onCreateLayout();

    //跳转
    protected void skipActivity(Class cls, int ResultCode, Intent intent, Bundle bundle) {
        intent.setClass(activity, cls);
        if (bundle != null) intent.putExtras(bundle);
        if (BasicActivity.NORESULTCODE != ResultCode) {
            activity.startActivityForResult(intent, ResultCode);
        } else {
            activity.startActivity(intent);
        }
        mBundle = null;
    }

    //跳转 带返回参数
    protected void skipActivity(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode, new Intent(), mBundle);
    }

    //普通跳转
    protected void skipActivity(Class cls) {
        skipActivity(cls, BasicActivity.NORESULTCODE, new Intent(), mBundle);
    }

    //跳转并且finish
    protected void skipActivityFinish(Class cls) {
        skipActivity(cls);
        activity.finish();
    }

    //跳转 带返回参数 并且finish
    protected void skipActivityFinish(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode);
        activity.finish();
    }

    //finish
    protected void finish() {
        activity.finish();
    }

    //finish 带返回码
    protected void finish(int result) {
        activity.setResult(result);
        activity.finish();
    }

    protected void toast(@StringRes int string_id) {
        toast(mContext.getString(string_id));
    }

    protected void toast(String text) {
        if (text != null) {
            toast(text, ToastUtil.TOAST_SUCCEED);
        }
    }

    protected void toast(@StringRes int string_id, @ToastUtil.ToastType int toast_type) {
        toast(mContext.getString(string_id), toast_type);
    }

    protected void toast(String text, @ToastUtil.ToastType int toast_type) {
        if (text != null) {
            BaseApplication.getInstance().showToast((Activity) mContext, text, toast_type);
        }
    }

}