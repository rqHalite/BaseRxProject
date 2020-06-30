package com.rock.basemodel.baseui.adapter;

import android.text.Html;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.IdRes;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseViewHolder;

/**
 * 拓展功能的ViewHolder
 * Created by Administrator on 2018/3/7.
 */

public class BasicViewHolder extends BaseViewHolder {

    public BasicViewHolder(View view) {
        super(view);
    }

    public BasicViewHolder setHttpText(@IdRes int viewId, String text) {
        TextView view = getView(viewId);
        if (view != null) view.setText(Html.fromHtml(text));
        return this;
    }

    public BasicViewHolder setEnabled(@IdRes int viewId, boolean enabled) {
        View view = getView(viewId);
        view.setEnabled(enabled);
        return this;
    }

    public BasicViewHolder setRecyclerViewAdapter(int viewId, RecyclerView.Adapter adapter) {
        RecyclerView view = getView(viewId);
        view.setAdapter(adapter);
        return this;
    }

    public BasicViewHolder setRecyclerViewLayoutManager(int viewId, RecyclerView.LayoutManager layoutManager) {
        RecyclerView view = getView(viewId);
        view.setLayoutManager(layoutManager);
        return this;
    }
}
