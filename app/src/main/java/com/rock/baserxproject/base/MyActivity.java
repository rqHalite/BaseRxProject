package com.rock.baserxproject.base;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.rock.basemodel.baseui.ui.BasicTitleActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


/**
 * @author Ruanqi
 * @time 2019/7/31
 * 所有activity都继承于他
 */
public abstract class MyActivity extends BasicTitleActivity {
    private Unbinder mButterKnife;//View注解

    @Override
    protected void init(Bundle savedInstanceState) {
        mButterKnife = ButterKnife.bind(this);
        initOrientation();
    }

    /**
     * 初始化横竖屏方向，会和 LauncherTheme 主题样式有冲突，注意不要同时使用
     */
    protected void initOrientation() {
        // 当前 Activity 不能是透明的并且没有指定屏幕方向，默认设置为竖屏
        if (getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mButterKnife != null) mButterKnife.unbind();

    }
}
