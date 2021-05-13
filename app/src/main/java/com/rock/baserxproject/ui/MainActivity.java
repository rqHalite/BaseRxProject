package com.rock.baserxproject.ui;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.rock.baserxproject.R;
import com.rock.baserxproject.base.MyActivity;
import com.rock.baserxproject.bean.TabEntity;
import com.rock.baserxproject.ui.fragment.MainFragment;
import com.rock.baserxproject.ui.fragment.MessageFragment;
import com.rock.baserxproject.ui.fragment.MineFragment;
import com.rock.baserxproject.ui.fragment.PictureFragment;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends MyActivity {
    @BindView(R.id.tab_layout)
    CommonTabLayout tabLayout;
    private long exitTime = 0;
    //    @BindView(R.id.tab_layout)
//    CommonTabLayout tabLayout;
//    @BindView(R.id.main_layout)
//    FrameLayout mainLayout;
    private String[] mTitles = {"首页", "消息", "联系人", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private Fragment currentFragment = new Fragment();
    private FragmentTransaction transaction;
    private Fragment letFragment;

    @Override
    protected int onCreateLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        hideToolbarView();
        showStatusView(false);
        setRefreshEnable(false);
//        setStatusBarColor(R.color.colorPrimary);
        //viewpager方式
//        HomeFragmentAdapter adapter = new HomeFragmentAdapter(this);
//        mainScroll.setAdapter(adapter);
//        initTab();

        initFragment();
        setCurrentItem(0);
    }

    private void initFragment() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        tabLayout.setTabData(mTabEntities);
        //tabLayout 的点击事件
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
    }

    private void setCurrentItem(int i) {
        transaction = getSupportFragmentManager().beginTransaction();
        switch (i) {
            case 0:
//                transaction.replace(R.id.main_layout, MainFragment.newInstance());
//                transaction.commit();
                //add,hide方式
                curtFragment(MainFragment.newInstance());
                break;
            case 1:
//                transaction.replace(R.id.main_layout, MessageFragment.newInstance());
//                transaction.commit();
                //add,hide方式
                curtFragment(MessageFragment.newInstance());
                break;
            case 2:
//                transaction.replace(R.id.main_layout, MineFragment.newInstance());
//                transaction.commit();
                //add,hide方式
                curtFragment(PictureFragment.newInstance());
                break;
            case 3:
//                transaction.replace(R.id.main_layout, MineFragment.newInstance());
//                transaction.commit();
                //add,hide方式
                curtFragment(MineFragment.newInstance());
                break;
        }
    }

    public void curtFragment(RxFragment targetFragment) {
        //
        if (targetFragment != currentFragment) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!targetFragment.isAdded()) { // 判断是否被add过
                // 隐藏当前的fragment，将 下一个fragment 添加进去
                transaction.hide(currentFragment).add(R.id.main_layout, targetFragment).commit();
            } else {
                // 隐藏当前的fragment，显示下一个fragment
                transaction.hide(currentFragment).show(targetFragment).commit();
            }
            currentFragment = targetFragment;
        }

    }

    //viewpager方式
//    private void initTab() {
//        for (int i = 0; i < mTitles.length; i++) {
//            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
//        }
//        tabLayout.setTabData(mTabEntities);
//        //tabLayout 的点击事件
//        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
//            @Override
//            public void onTabSelect(int position) {
//                mainScroll.setCurrentItem(position);
//            }
//
//            @Override
//            public void onTabReselect(int position) {
//
//            }
//        });
//
//        mainScroll.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int i, float v, int i1) {
//
//            }
//
//            @Override
//            public void onPageSelected(int i) {
//                tabLayout.setCurrentTab(i);
////                tabLayout.hideMsg(i);
//
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int i) {
//
//            }
//        });
//        mainScroll.setCurrentItem(0);
//    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                toast("确定退出当前页面吗？");
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
