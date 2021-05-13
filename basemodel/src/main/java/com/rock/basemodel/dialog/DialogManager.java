package com.rock.basemodel.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;

import com.rock.basemodel.baseui.ui.BasicDialog;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;


/**
 * created by zhud on 2018/12/15
 */
public class DialogManager {
    private static DialogManager dialogManager;
    private BaseDialog loadingDialog;
    private BaseDialog dialog;

    public static synchronized DialogManager getInstance() {
        if (dialogManager == null) {
            dialogManager = new DialogManager();
        }
        return dialogManager;
    }

    /**
     * 显示加载中弹窗
     *
     * @param context
     */
    public void showLoading(Context context) {
        dismissLoadingDialog();
        loadingDialog = new LoadingDialog(context);
        loadingDialog.show();
    }

    //销毁当前显示的dialog
    private void dismiss(BasicDialog dialog) {
        if (dialog != null && dialog.isShowing()) {
            Context context = ((ContextWrapper) dialog.getContext()).getBaseContext();
            if (!((RxAppCompatActivity) context).isFinishing() && !((RxAppCompatActivity) context).isDestroyed()) {
                dialog.dismiss();
            }
        }
    }

    public void dismissLoadingDialog() {
        dismiss(loadingDialog);
        loadingDialog = null;
    }
}
