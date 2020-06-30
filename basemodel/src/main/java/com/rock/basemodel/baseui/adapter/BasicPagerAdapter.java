package com.rock.basemodel.baseui.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.rock.basemodel.screentools.ScreenAdapterTools;


/**
 * created by zhud on 2018/12/13
 */
public abstract class BasicPagerAdapter extends PagerAdapter {

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = onCreateView(container);
        ScreenAdapterTools.getInstance().loadView(view);
        return view;
    }


    public abstract View onCreateView(ViewGroup container);

}