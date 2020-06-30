package com.rock.basemodel.baseui.adapter;

import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseSectionQuickAdapter;
import com.chad.library.adapter.base.entity.SectionEntity;
import com.rock.basemodel.screentools.ScreenAdapterTools;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9.
 */

public abstract class BasicSectionQuickAdapter<T extends SectionEntity, K extends BasicViewHolder> extends BaseSectionQuickAdapter<T, K> {
    public BasicSectionQuickAdapter(int layoutResId, int sectionHeadResId, List<T> data) {
        super(layoutResId, sectionHeadResId, data);
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
