package com.rock.basemodel.baseui.adapter;

import android.view.ViewGroup;

import androidx.annotation.LayoutRes;

import com.chad.library.adapter.base.BaseItemDraggableAdapter;
import com.rock.basemodel.screentools.ScreenAdapterTools;

import java.util.List;

/**
 * 侧滑删除以及拖拽
 * created by zhud on 2018/12/15
 */
public class BasicItemDraggableAdapter<T, K extends BasicViewHolder> extends BaseItemDraggableAdapter<T, K> {
    public BasicItemDraggableAdapter(@LayoutRes int layoutResId, List<T> data) {
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
