package com.rock.basemodel.baseui.utils;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.IntDef;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.rock.basemodel.R;
import com.rock.basemodel.screentools.ScreenAdapterTools;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Ruanqi
 * @time 2019/7/29
 * 自定义吐司
 */
public class ToastUtil {

    @IntDef({TOAST_SUCCEED, TOAST_ERROR})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ToastType {
    }

    public final static int TOAST_SUCCEED = 0;
    public final static int TOAST_ERROR = 1;

    public static void show(Activity view, String content) {
        show(view, content, TOAST_SUCCEED);
    }

    public static void show(Activity view, String content, @ToastType int toast_type) {
        //如果手机弹窗权限被禁，用SnackBar代替Toast
        if (!PermissionUtils.isNotificationEnabled(view)) {
            Snackbar snackbar = Snackbar.make(view.getWindow().getDecorView(), content, Snackbar.LENGTH_SHORT);
            View snackbarView = snackbar.getView();//获取SnackBar布局View实例
            TextView textView = snackbarView.findViewById(R.id.snackbar_text);//获取文本View实例
            Button button = snackbarView.findViewById(R.id.snackbar_action);//获取按钮View实例
            button.setTextColor(Color.WHITE);
            snackbarView.setBackgroundColor(ContextCompat.getColor(view, R.color.toast_background));//更改背景颜色
            textView.setTextColor(Color.WHITE);//更改文本颜色
            snackbar.show();
        } else {
            View contentView = View.inflate(view, R.layout.scorpio_layout_toast, null);
            ScreenAdapterTools.getInstance().loadView(contentView);
            TextView msgText = contentView.findViewById(R.id.message_operateResult);
            ImageView imageView = contentView.findViewById(R.id.icon_operateResult);
            msgText.setText(content);
            int resid = R.drawable.icon_succeed;
            if (toast_type == TOAST_ERROR) {
                resid = R.drawable.icon_error;
            }
            imageView.setImageResource(resid);
            Toast toast = new Toast(view);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(contentView);
            toast.setGravity(Gravity.TOP, 0, ScreenAdapterTools.getInstance().loadCustomAttrValue(50));
            toast.show();
        }
    }
}

