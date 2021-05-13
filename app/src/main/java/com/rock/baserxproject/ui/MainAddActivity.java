package com.rock.baserxproject.ui;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.KeyEvent;

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

import java.util.ArrayList;

import butterknife.BindView;

public class MainAddActivity extends MyActivity {

    @BindView(R.id.tab_layout1)
    CommonTabLayout tabLayout;
    private long exitTime = 0;
    private FragmentManager fm = getSupportFragmentManager();
    private String[] mTitles = {"首页", "消息", "联系人", "我的"};
    private int[] mIconUnselectIds = {
            R.mipmap.tab_home_unselect, R.mipmap.tab_speech_unselect,
            R.mipmap.tab_contact_unselect, R.mipmap.tab_more_unselect};
    private int[] mIconSelectIds = {
            R.mipmap.tab_home_select, R.mipmap.tab_speech_select,
            R.mipmap.tab_contact_select, R.mipmap.tab_more_select};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private Fragment letFragment;

    @Override
    protected int onCreateLayout() {
        return R.layout.activity_main_add;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        hideToolbarView();
        showStatusView(false);
        setRefreshEnable(false);
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
        switch (i) {
            case 0:
                switchTo(0);
                break;
            case 1:
                switchTo(1);
                break;
            case 2:
                switchTo(2);
                break;
            case 3:
                switchTo(3);
                break;
        }
    }

    private void switchTo(int position) {
        String tag = position + "";
        Fragment fragment = fm.findFragmentByTag(tag);
        if (fragment == null) {
            switch (position) {
                case 0:
                    fragment = new MainFragment();
                    fm.beginTransaction().add(R.id.main_layout1, fragment, tag).commit();
                    break;
                case 1:
                    fragment = new MessageFragment();
                    fm.beginTransaction().add(R.id.main_layout1, fragment, tag).commit();
                    break;
                case 2:
                    fragment = new PictureFragment();
                    fm.beginTransaction().add(R.id.main_layout1, fragment, tag).commit();
                    break;
                case 3:
                    fragment = new MineFragment();
                    fm.beginTransaction().add(R.id.main_layout1, fragment, tag).commit();
                    break;
            }

        }
        if (letFragment != null) fm.beginTransaction().hide(letFragment).commit();
        letFragment = fragment;
        fm.beginTransaction().show(fragment).commit();
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