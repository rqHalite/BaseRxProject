package com.rock.baserxproject.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.List;

/**
 * File descripition:
 *
 * @author Administrator
 * @date 2018/5/30
 * fragmentPagerAdapter 适用于数量少的fragment 场景
 * fragmentStatePagerAdapter 适用于数量多的fragment场景
 */


public class ViewPagerAdapter extends FragmentPagerAdapter {

    private List<RxFragment> mFragments;
    private List<String> mTitle;
    private FragmentManager fm;

    public ViewPagerAdapter(FragmentManager fm, List<RxFragment> fragments, List<String> title) {
        super(fm);
        this.fm = fm;
        this.mFragments = fragments;
        this.mTitle = title;
    }

    @Override
    public RxFragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitle == null ? "" : mTitle.get(position);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        FragmentTransaction ft = fm.beginTransaction();
        for (int i = 0; i < getCount(); i++) {//通过遍历清除所有缓存
            final long itemId = getItemId(i);
            //得到缓存fragment的名字
            String name = makeFragmentName(container.getId(), itemId);
            Fragment fragment = fm.findFragmentByTag(name);
            if (fragment != null) {
                //移除之前的fragment
                ft.remove(fragment);
            }
        }
        //重新添加新的fragment:最后记得commit
        ft.add(container.getId(), getItem(position)).attach(getItem(position)).commitAllowingStateLoss();
        return getItem(position);
    }

    public long getItemId(int position) {
        return mFragments.get(position).hashCode();
    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    private String makeFragmentName(int viewId, long id) {
        return "android:switcher:" + viewId + ":" + id;
    }
}

