package com.rock.baserxproject.ui.fragment;


import android.graphics.Bitmap;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.rock.basemodel.http.RxNetWorkUtil;
import com.rock.basemodel.http.retrofit.rx.MyObserver;
import com.rock.baserxproject.R;
import com.rock.baserxproject.adapter.PicListAdapter;
import com.rock.baserxproject.base.MyFragment;
import com.rock.baserxproject.bean.HttpBean;
import com.rock.baserxproject.bean.PictureListBean;
import com.rock.baserxproject.http.RxAppNetWorkUtils;
import com.rock.baserxproject.utils.ImageLoader;
import com.rock.baserxproject.utils.NoDefaultItemAnimator;
import com.rock.baserxproject.utils.StaggeredDividerItemDecoration;
import com.rock.baserxproject.view.CustomLoadMoreView;
import com.rock.baserxproject.view.dialog.ImagePagerDialog;
import com.rock.baserxproject.view.dialog.PictureDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PictureFragment extends MyFragment implements BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener {


    @BindView(R.id.pic_list)
    RecyclerView picList;

    private List<String> images = new ArrayList<>();
    private List<HttpBean.DataBean> mDatas = new ArrayList<>();
    private PicListAdapter mAdapter;
    private int page = 50;

    public static PictureFragment newInstance() {
        // Required empty public constructor
        PictureFragment fragment = new PictureFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_picture;
    }

    @Override
    protected void initData() {

        StaggeredGridLayoutManager manager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
        manager.setGapStrategy(StaggeredGridLayoutManager.GAP_HANDLING_NONE);
        picList.setLayoutManager(manager);
        picList.setAdapter(mAdapter = new PicListAdapter(mDatas));
        picList.setItemAnimator(new NoDefaultItemAnimator());
        mAdapter.setOnLoadMoreListener(this);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.setOnItemChildClickListener(this);
        //解决顶部白块
        picList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                int[] first = new int[2];
                manager.findFirstCompletelyVisibleItemPositions(first);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (first[0] == 1 || first[1] == 1)) {
                    manager.invalidateSpanAssignments();
                }
            }
        });
        //解决上下间隔
        picList.addItemDecoration(new StaggeredDividerItemDecoration(getActivity(), 5));
        datas();
    }

    private void datas() {
        Map<String, String> map = new HashMap<>();
        map.put("count", page + "");
        RxAppNetWorkUtils.getPicList(this, map, new MyObserver(getActivity(), false) {
            @Override
            public void onSuccess(Object demo) {
                HttpBean bean = (HttpBean) demo;
                List<HttpBean.DataBean> results = bean.getData();

                if (results.size() < 1) {
                    mAdapter.loadMoreEnd();
                } else {
                    for (HttpBean.DataBean data : results) {
                        Bitmap bitmap = ImageLoader.load(getActivity(), data.getUrl());
                        if (bitmap != null) {
                            data.setWidth(bitmap.getWidth());
                            data.setHight(bitmap.getHeight());
                        }
                    }
                    page += 4;
                    mDatas.addAll(results);
                    for (HttpBean.DataBean b : mDatas) {
                        images.add(b.getUrl());
                    }
                    mAdapter.loadMoreComplete();
                    //添加防止刷新闪动
                    ((SimpleItemAnimator) picList.getItemAnimator()).setSupportsChangeAnimations(false);
                    if (!mAdapter.hasObservers()) {
                        mAdapter.setHasStableIds(true);
                    }
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Throwable e, String errorMsg) {
                mAdapter.loadMoreFail();
            }
        });
    }

    @Override
    public void onLoadMoreRequested() {
        datas();
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        ImagePagerDialog imagePagerDialog = new ImagePagerDialog(getContext());
        List<String> imageList = new ArrayList<>();
        imageList.addAll(images);
        imagePagerDialog.show(position, imageList);
    }
}
