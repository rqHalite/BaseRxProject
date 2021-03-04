package com.rock.baserxproject.ui.fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.google.android.material.appbar.AppBarLayout;
import com.rock.basemodel.baseui.utils.BarTextColorUtils;
import com.rock.baserxproject.R;
import com.rock.baserxproject.adapter.ViewPagerAdapter;
import com.rock.baserxproject.base.MyFragment;
import com.rock.baserxproject.bean.TabEntity;
import com.rock.baserxproject.utils.DisplayUtil;
import com.rock.baserxproject.utils.ScreenUtil;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.SimpleMultiPurposeListener;
import com.trello.rxlifecycle2.components.support.RxFragment;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 */
public class MessageFragment extends MyFragment implements OnRefreshListener, AppBarLayout.OnOffsetChangedListener {


    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;
    @BindView(R.id.iv_header)
    ImageView mIvHeader;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.toolbar_username)
    TextView mToolbarUsername;
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    @BindView(R.id.iv_menu)
    ImageView mIvMenu;
    @BindView(R.id.toolbar1)
    Toolbar mToolbar1;
    Unbinder unbinder;
    @BindView(R.id.magic_indicator)
    CommonTabLayout magicIndicator;
    @BindView(R.id.top_status)
    View topStatus;
    private List<RxFragment> mFragments = new ArrayList<>();
    private ViewPagerAdapter mViewPagerAdapter;
    private CommonNavigator mCommonNavigator;
    private String[] titles = new String[]{"android", "ios", "flutter", "更多"};
    private List<String> mTitles = Arrays.asList(titles);
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int mScreenWidth = 0;

    public static MessageFragment newInstance() {
        // Required empty public constructor
        MessageFragment fragment = new MessageFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initData() {
        int statusheight = BarTextColorUtils.getStatusBarHeight(mActivity);
        if (statusheight != -1) {
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
            topStatus.setLayoutParams(params);
            topStatus.setBackgroundColor(ContextCompat.getColor(mActivity, R.color.toolbar_tint_color));
        }
        //禁止上拉加载
        mRefreshLayout.setEnableLoadMore(false);
        mRefreshLayout.setOnRefreshListener(this);
        //上滑移动 设置监听
        mAppbarLayout.addOnOffsetChangedListener(this);


        //获得屏幕宽度
        mScreenWidth = ScreenUtil.getScreenWidth(getContext());
        intFragment();
        initMagicIndicator();
        initView();

        mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), mFragments, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
    }

    private void intFragment() {
        mFragments.clear();

        for (int i = 0; i < mTitles.size(); i++) {
            String type = "";
            if (i == 0) {
                type = "Android";
            } else if (i == 1) {
                type = "iOS";
            } else if (i == 2) {
                type = "Flutter";
            } else {
                type = "Girl";
            }
            mFragments.add(DynamicFragment.newInstance(type));
        }
    }

    private void initView() {
        mRefreshLayout.setOnMultiPurposeListener(new SimpleMultiPurposeListener() {
            @Override
            public void onHeaderMoving(RefreshHeader header, boolean isDragging, float percent, int offset, int headerHeight, int maxDragHeight) {
                //设置图片向下移动
                mIvHeader.setTranslationY(offset / 2);
                //设置title渐变效果
                mToolbar1.setAlpha(1 - Math.min(percent, 1));
                //设置图片宽度变化   当达到指定设置的指定值后  宽度停止  只向下移动
                if (offset <= 100) {
                    ViewGroup.LayoutParams layoutParams = mIvHeader.getLayoutParams();
                    layoutParams.width = (mScreenWidth + offset);
                    ((ViewGroup.MarginLayoutParams) layoutParams).setMargins(-(layoutParams.width - mScreenWidth) / 2, -DisplayUtil.dip2px(getActivity(), 200), 0, 0);
                    mIvHeader.requestLayout();
                }
            }
        });
    }

    private void initMagicIndicator() {
        for (int i = 0; i < mTitles.size(); i++) {
            mTabEntities.add(new TabEntity(mTitles.get(i)));
        }

//        mMagicIndicator.setBackgroundColor(getResources().getColor(R.color.segmentation_line));
//        mCommonNavigator = new CommonNavigator(getContext());
//        mCommonNavigator.setAdjustMode(true);
//        mCommonNavigator.setAdapter(new CommonNavigatorAdapter() {
//            @Override
//            public int getCount() {
//                return mTitleList == null ? 0 : mTitleList.size();
//            }
//
//            @Override
//            public IPagerTitleView getTitleView(Context context, final int index) {
//                SimplePagerTitleView simplePagerTitleView = new ColorFlipPagerTitleView(context);
//                simplePagerTitleView.setText(mTitleList.get(index));
//                simplePagerTitleView.setTextSize(14);
//                simplePagerTitleView.setNormalColor(Color.BLACK);
//                simplePagerTitleView.setSelectedColor(getResources().getColor(R.color.colorAccent));
//                simplePagerTitleView.setNormalColor(getResources().getColor(R.color.colorPrimary));
//                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        mViewPager.setCurrentItem(index);
//                    }
//                });
//                return simplePagerTitleView;
//            }
//
//            @Override
//            public IPagerIndicator getIndicator(Context context) {
//                LinePagerIndicator indicator = new LinePagerIndicator(context);
//                indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
//                indicator.setLineHeight(UIUtil.dip2px(context, 2));
//                indicator.setLineWidth(UIUtil.dip2px(context, 30));
//                indicator.setRoundRadius(UIUtil.dip2px(context, 3));
//                indicator.setStartInterpolator(new AccelerateInterpolator());
//                indicator.setEndInterpolator(new DecelerateInterpolator(2.0f));
//                indicator.setColors(getResources().getColor(R.color.colorAccent));
//                return indicator;
//            }
//        });
//
//        mMagicIndicator.setNavigator(mCommonNavigator);
//        ViewPagerHelper.bind(mMagicIndicator, mViewPager);
        magicIndicator.setTabData(mTabEntities);
        magicIndicator.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                magicIndicator.setCurrentTab(i);

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mViewPager.setCurrentItem(0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        refreshLayout.finishRefresh(1000);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

        int scrollRangle = appBarLayout.getTotalScrollRange();
        /**
         * 如果是verticalOffset改成负数   有不一样的效果，可以模拟试试
         */
        mIvHeader.setTranslationY(verticalOffset);

        /**
         * 这个数值可以自己定义
         */
//        if (verticalOffset < -10) {
//            mIvBack.setImageResource(R.drawable.back_black);
//            mIvMenu.setImageResource(R.drawable.icon_menu_black);
//        } else {
//            mIvBack.setImageResource(R.drawable.back_white);
//            mIvMenu.setImageResource(R.drawable.icon_menu_white);
//        }

        int mAlpha = (int) Math.abs(255f / scrollRangle * verticalOffset);
        //顶部title渐变效果
        mToolbar1.setBackgroundColor(Color.argb(mAlpha, 255, 255, 255));
        mToolbarUsername.setTextColor(Color.argb(mAlpha, 0, 0, 0));
    }
}
