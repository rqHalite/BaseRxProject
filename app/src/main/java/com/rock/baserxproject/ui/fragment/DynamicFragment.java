package com.rock.baserxproject.ui.fragment;


import android.os.Bundle;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.http.retrofit.rx.MyObserver;
import com.rock.basemodel.webview.X5WebActivity;
import com.rock.baserxproject.R;
import com.rock.baserxproject.adapter.MainAdapter;
import com.rock.baserxproject.base.MyFragment;
import com.rock.baserxproject.bean.HttpBean;
import com.rock.baserxproject.http.RxAppNetWorkUtils;
import com.rock.baserxproject.view.CustomLoadMoreView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 *
 */
public class DynamicFragment extends MyFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.dy_mList)
    RecyclerView dyMList;
    private String mTitle = "";
    private MainAdapter mAdapter;
    private List<HttpBean.DataBean> mData = new ArrayList<>();
    private int number = 2;
    private int page = 1;
    public static DynamicFragment newInstance(String title) {
        // Required empty public constructor
        DynamicFragment fragment = new DynamicFragment();
        fragment.mTitle = title;
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initData() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        dyMList.setLayoutManager(manager);
        dyMList.setAdapter(mAdapter = new MainAdapter(mData));
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        http();
    }
    private void http() {
        Map<String, String> map = new HashMap<>();
        map.put("count", "" + number);
        map.put("page", "" + page);
        map.put("type", mTitle);
        RxAppNetWorkUtils.getTestList(this, map, new MyObserver(getActivity(),false) {
            @Override
            public void onSuccess(Object demo) {
                HttpBean bean = (HttpBean) demo;
                List<HttpBean.DataBean> results = bean.getData();
                if (results.size() < 1) {
                    mAdapter.loadMoreEnd();
                } else {
                    page++;
                    mData.addAll(results);
                    mAdapter.notifyDataSetChanged();
                    mAdapter.loadMoreComplete();
                }
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                mAdapter.loadMoreFail();
                ToastUtil.show(getActivity(),"失败");
            }
        });
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//        ToastUtils.show("打开网页");
        toast("打开网页");
        mBundle = new Bundle();
        mBundle.putString(X5WebActivity.WEB_URL,mData.get(position).getUrl());//
        skipActivity(X5WebActivity.class);
    }

    @Override
    public void onLoadMoreRequested() {
        http();
    }

}
