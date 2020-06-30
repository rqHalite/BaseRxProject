package com.rock.basemodel.baseui.ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.R;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.screentools.ScreenAdapterTools;


/**
 * created by zhud on 2018/10/25
 */
public abstract class BasicDialog extends Dialog {
    protected View mView;
    protected Context mContext;
    protected Bundle mBundle;
    private Activity activity;

    public BasicDialog(@NonNull Context context) {
        this(context, R.style.UniversalDialogStyle);
    }

    public BasicDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.mContext = context;
        this.activity = (Activity) context;
        this.mBundle = new Bundle();
        mView = LayoutInflater.from(context).inflate(onCreateLayout(), null);
        setContentView(mView);
        setCanceledOnTouchOutside(false);//击屏幕，dialog不消失；点击物理返回键dialog消失
        ScreenAdapterTools.getInstance().loadView(mView);
    }

    protected abstract int onCreateLayout();

    //跳转
    public void skipActivity(Class cls, int ResultCode, Intent intent, Bundle bundle) {
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
            BaseApplication.getInstance().showToast(getOwnerActivity(), text, toast_type);
        }
    }

    /**
     * 居中显示
     */
    protected void initCenterLayout() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 初始化居中全屏显示
     */
    protected void initCenterMatchLayout() {
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialogWindow.setAttributes(lp);
    }

    /**
     * 底部显示Dialog
     */
    protected void initBottomLayout() {
        Window dialogWindow = getWindow();
        dialogWindow.setWindowAnimations(R.style.dialog_animation);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.gravity = Gravity.BOTTOM;
        dialogWindow.setAttributes(lp);
    }
}