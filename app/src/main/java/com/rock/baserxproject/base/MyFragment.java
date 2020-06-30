package com.rock.baserxproject.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;

import com.rock.basemodel.baseui.ui.BasicFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author Ruanqi
 * @time 2019/7/31
 */
public abstract class MyFragment extends BasicFragment {

    private Unbinder mButterKnife;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        mButterKnife = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) mButterKnife.unbind();
    }
}
