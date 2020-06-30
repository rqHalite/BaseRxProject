package com.rock.basemodel.baseui.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.rock.basemodel.screentools.ScreenAdapterTools;

import java.util.List;

/**
 * 多布局适配器
 * Created by St on 2018/4/19.
 */

public abstract class BasicMultiItemQuickAdapter<T extends MultiItemEntity, K extends BasicViewHolder> extends BaseMultiItemQuickAdapter<T, K> {
    public BasicMultiItemQuickAdapter(List data) {
        super(data);
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
