package com.rock.basemodel.dialog;

import android.content.Context;

import androidx.annotation.NonNull;

import com.rock.basemodel.R;

/**
 * created by zhud on 2018/12/20
 */
public class LoadingDialog extends BaseDialog {

    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.UniversalDialogStyleNoBackground);
        initCenterLayout();
    }

    @Override
    protected int onCreateLayout() {
        return R.layout.dialog_loading_view;
    }
}
