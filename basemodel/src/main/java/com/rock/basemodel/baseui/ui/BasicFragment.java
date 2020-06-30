package com.rock.basemodel.baseui.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.baseui.utils.ToastUtil;
import com.rock.basemodel.screentools.ScreenAdapterTools;
import com.rock.basemodel.umeng.UmengUtils;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.Field;

public abstract class BasicFragment extends RxFragment {
    protected Context mContext;
    protected Bundle mBundle;
    public boolean isDestroy = false;
    // Activity对象
    public RxAppCompatActivity mActivity;
    // 根布局
    private View mRootView;
    // 是否进行过懒加载
    private boolean isLazyLoad;
    // Fragment 是否可见
    private boolean isFragmentVisible;
    // 是否是 replace Fragment 的形式
    private boolean isReplaceFragment;
    private SmartRefreshLayout refreshLayout;

    /**
     * 获得全局的，防止使用getActivity()为空
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (RxAppCompatActivity) context;
    }

    /**
     * 获取Activity，防止出现 getActivity() 为空
     */
    public RxAppCompatActivity getFragmentActivity() {
        return mActivity;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // super.onCreateView(inflater, container, savedInstanceState);

        if (mRootView == null && getLayoutId() > 0) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
            ScreenAdapterTools.getInstance().loadView(mRootView);
            mContext = container.getContext();
        }

        ViewGroup parent = (ViewGroup) mRootView.getParent();
        if (parent != null) {
            parent.removeView(mRootView);
        }
        return mRootView;
    }

    private void setRefreshEnable(boolean b) {
        refreshLayout.setEnableRefresh(b);
    }


    @Override
    public View getView() {
        return mRootView;
    }

    /**
     * 是否进行了懒加载
     */
    protected boolean isLazyLoad() {
        return isLazyLoad;
    }

    /**
     * 当前 Fragment 是否可见
     */
    public boolean isFragmentVisible() {
        return isFragmentVisible;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (isReplaceFragment) {
            if (isFragmentVisible) {
                initLazyLoad();
            }
        } else {
            initLazyLoad();

        }
    }

    // replace Fragment时使用，ViewPager 切换时会回调此方法
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        this.isReplaceFragment = true;
        this.isFragmentVisible = isVisibleToUser;
        if (isVisibleToUser && getView() != null) {
            if (!isLazyLoad) {
                initLazyLoad();
            } else {
                // 从不可见到可见
                onRestart();
            }
        }
    }


    /**
     * 初始化懒加载
     */
    protected void initLazyLoad() {
        if (!isLazyLoad) {
            isLazyLoad = true;
            initData();
        }
    }

    /**
     * 跟 Activity 的同名方法效果一样
     */
    protected void onRestart() {
        // 从可见的状态变成不可见状态，再从不可见状态变成可见状态时回调
    }

    @Override
    public void onDetach() {
        super.onDetach();
        //解决java.lang.IllegalStateException: Activity has been destroyed 的错误
        try {
            if (getActivity() != null && getActivity().isFinishing()) {
                Field childFragmentManager = RxFragment.class.getDeclaredField("mChildFragmentManager");
                childFragmentManager.setAccessible(true);
                childFragmentManager.set(this, null);
            }
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


    //引入布局
    protected abstract int getLayoutId();

    //初始化数据
    protected abstract void initData();


    //跳转
    public void skipActivity(Class cls, int ResultCode, Intent intent, Bundle bundle) {
        intent.setClass(getActivity(), cls);
        if (bundle != null) intent.putExtras(bundle);
        if (BasicActivity.NORESULTCODE != ResultCode) {
            startActivityForResult(intent, ResultCode);
        } else {
            startActivity(intent);
        }
        mBundle = null;
    }

    //跳转 带返回参数
    protected void skipActivity(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode, new Intent(), mBundle);
    }

    //普通跳转
    protected void skipActivity(Class cls) {
        skipActivity(cls, BasicActivity.NORESULTCODE, new Intent(), mBundle);
    }

    //跳转并且finish
    protected void skipActivityFinish(Class cls) {
        skipActivity(cls);
        getActivity().finish();
    }

    //跳转 带返回参数 并且finish
    protected void skipActivityFinish(Class cls, int ResultCode) {
        skipActivity(cls, ResultCode);
        getActivity().finish();
    }

    //finish
    protected void finish() {
        getActivity().finish();
    }

    //finish 带返回码
    protected void finish(int result) {
        Intent intent = new Intent();
        if (mBundle != null) intent.putExtras(mBundle);
        getActivity().setResult(result, intent);
        getActivity().finish();
    }

    protected void toast(@StringRes int string_id) {
        toast(mContext.getString(string_id));
    }

    protected void toast(String text) {
        if (text != null) {
            toast(text, ToastUtil.TOAST_SUCCEED);
        }
    }

    protected void toast(@StringRes int string_id, @ToastUtil.ToastType int toast_type) {
        toast(mContext.getString(string_id), toast_type);
    }


    protected void toast(String text, @ToastUtil.ToastType int toast_type) {
        if (text != null) {
            BaseApplication.getInstance().showToast(getActivity(), text, toast_type);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isDestroy = true;
    }

    @Override
    public void onResume() {
        super.onResume();
        UmengUtils.onResume(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        UmengUtils.onPause(this);
    }
}