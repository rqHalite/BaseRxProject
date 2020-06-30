package com.rock.basemodel.baseui.adapter;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rock.basemodel.screentools.ScreenAdapterTools;

import java.util.List;

/**
 * Created by St on 2018/4/10.
 */

public abstract class BasicQuickAdapter<T, K extends BasicViewHolder> extends BaseQuickAdapter<T, K> {
    public BasicQuickAdapter(@LayoutRes int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }

    @Override
    public K onCreateViewHolder(ViewGroup parent, int viewType) {
        K baseViewHolder = super.onCreateViewHolder(parent, viewType);
        ScreenAdapterTools.getInstance().loadView(baseViewHolder.itemView);
        return baseViewHolder;
    }

    @Override
    protected void convert(K helper, T item) {
    }


}
