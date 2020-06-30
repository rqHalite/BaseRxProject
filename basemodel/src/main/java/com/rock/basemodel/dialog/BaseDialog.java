package com.rock.basemodel.dialog;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StyleRes;

import com.rock.basemodel.baseui.ui.BasicDialog;

/**
 * created by zhud on 2018/10/26
 */
public abstract class BaseDialog extends BasicDialog {
    public BaseDialog(@NonNull Context context) {
        super(context);
    }

    public BaseDialog(Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }
}
