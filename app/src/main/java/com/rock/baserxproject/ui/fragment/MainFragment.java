package com.rock.baserxproject.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.loadmore.LoadMoreView;
import com.hjq.toast.ToastUtils;
import com.rock.basemodel.baseui.utils.BarTextColorUtils;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.baseui.utils.view.MyRefreshHeader;
import com.rock.basemodel.http.RxNetWorkUtil;
import com.rock.basemodel.http.retrofit.rx.MyObserver;
import com.rock.basemodel.webview.X5WebActivity;
import com.rock.baserxproject.R;
import com.rock.baserxproject.adapter.MainAdapter;
import com.rock.baserxproject.base.MyFragment;
import com.rock.baserxproject.bean.HttpBean;
import com.rock.baserxproject.http.RxAppNetWorkUtils;
import com.rock.baserxproject.utils.StatusBarUtil;
import com.rock.baserxproject.view.CustomLoadMoreView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 首页
 */
public class MainFragment extends MyFragment implements BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.mList)
    RecyclerView mList;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;
    Unbinder unbinder;
    @BindView(R.id.top_status)
    View topStatus;
    @BindView(R.id.main_toplayout)
    View mainToplayout;
    @BindView(R.id.toolbar_username)
    TextView toolbarUsername;
    private MainAdapter mAdapter;
    private List<HttpBean.DataBean> mData = new ArrayList<>();
    private OnRefreshCallback onRefreshCallback;
    private int number = 2;
    private int page = 1;

    public static MainFragment newInstance() {
        // Required empty public constructor
        MainFragment fragment = new MainFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initData() {
        int statusheight = BarTextColorUtils.getStatusBarHeight(mActivity);
        if (statusheight != -1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
            topStatus.setLayoutParams(params);
            topStatus.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.toolbar_tint_color));
        }
        MyRefreshHeader refreshHeader = new MyRefreshHeader(getActivity(), MyRefreshHeader.TYPE_OTHER);
        refreshlayout.setRefreshHeader(refreshHeader);
        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (onRefreshCallback != null) {
                    onRefreshCallback.onRefresh();
                }
            }
        });
        toolbarUsername.setText("首页");
        refreshlayout.setEnableRefresh(true);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mList.setLayoutManager(manager);
        mList.setAdapter(mAdapter = new MainAdapter(mData));
        mAdapter.setOnItemChildClickListener(this);
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        setOnRefreshCallback(new OnRefreshCallback() {
            @Override
            public void onRefresh() {
                http();
            }
        });
        http();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private void http() {
        Map<String, String> map = new HashMap<>();
        map.put("count", "" + number);
        map.put("page", "" + page);
        map.put("type", "Android");
        RxAppNetWorkUtils.getTestList(this, map, new MyObserver(getActivity(),false) {
            @Override
            public void onSuccess(Object demo) {
                refreshlayout.finishRefresh(true);
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
        mBundle = new Bundle();
        mBundle.putString(X5WebActivity.WEB_URL,mData.get(position).getUrl());//
        skipActivity(X5WebActivity.class);
    }

    @Override
    public void onLoadMoreRequested() {
        http();
    }

    public interface OnRefreshCallback {
        void onRefresh();
    }

    public void setOnRefreshCallback(OnRefreshCallback onRefreshCallback) {
        this.onRefreshCallback = onRefreshCallback;
    }
}
