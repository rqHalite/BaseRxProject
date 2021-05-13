package com.rock.basemodel.baseui.adapter;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * FragmentPagerAdapter fragment数量少使用
 * 数量多使用 FragmentStatePagerAdapter
 */
public abstract class BasicFragmentAdapter<T extends Fragment> extends FragmentPagerAdapter {

    private List<T> mFragmentSet = new ArrayList<>(); // Fragment集合

    private T mCurrentFragment; // 当前显示的Fragment

    /**
     * 在Activity中使用ViewPager适配器
     */
    public BasicFragmentAdapter(FragmentActivity activity) {
        this(activity.getSupportFragmentManager());
    }

    /**
     * 在Fragment中使用ViewPager适配器
     */
    public BasicFragmentAdapter(Fragment fragment) {
        this(fragment.getChildFragmentManager());
    }

    public BasicFragmentAdapter(FragmentManager manager) {
        super(manager);
        init(manager, mFragmentSet);
    }

    //初始化Fragment
    protected abstract void init(FragmentManager manager, List<T> list);

    @Override
    public T getItem(int position) {
        return mFragmentSet.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentSet.size();
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        if (getCurrentFragment() != object) {
            // 记录当前的Fragment对象
            mCurrentFragment = (T) object;
        }
        super.setPrimaryItem(container, position, object);
    }

    /**
     * 获取Fragment集合
     */
    public List<T> getAllFragment() {
        return mFragmentSet;
    }

    /**
     * 获取当前的Fragment
     */
    public T getCurrentFragment() {
        return mCurrentFragment;
    }
}