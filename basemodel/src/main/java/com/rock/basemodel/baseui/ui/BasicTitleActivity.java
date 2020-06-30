package com.rock.basemodel.baseui.ui;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.R;
import com.rock.basemodel.baseui.utils.BarTextColorUtils;
import com.rock.basemodel.baseui.utils.view.MyRefreshHeader;
import com.rock.basemodel.screentools.ScreenAdapterTools;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;



/**
 * created by zhud on 2018/10/25
 */
public abstract class BasicTitleActivity extends BasicActivity implements View.OnClickListener {
    private View basestatus_view;
    private FrameLayout content_view, toproot_view;
    private LinearLayout back_btn;
    private TextView title_text;
    private TextView back_text;
    private ImageView back_image;
    private TextView menu_text;
    private ImageView menu_image;
    private LinearLayout menu_btn;
    private SmartRefreshLayout refreshLayout;
    private MyRefreshHeader refreshHeader;
    private OnRefreshCallback onRefreshCallback;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.scorpio_activity_basic);
        initView();
        initTopView();
        ScreenAdapterTools.getInstance().loadView(getWindow().getDecorView());
        resetStatusViewHeight();
        init(savedInstanceState);
    }

    protected abstract int onCreateLayout();

    protected abstract void init(Bundle savedInstanceState);

    //显示返回键
    protected void showBackBtn() {
        back_btn.setVisibility(View.VISIBLE);

    }

    //显示返回键
    protected void showMenuBtn() {
        menu_btn.setVisibility(View.VISIBLE);
    }

    protected void setBasicTitle(int res_id) {
        title_text.setText(res_id);
    }

    protected void setBasicTitle(String str) {
        title_text.setText(str);
    }

    protected void setBasicTitleColor(int color) {
        title_text.setTextColor(color);
    }

    protected void setBasicBackColor(int color) {
        back_text.setTextColor(color);
        back_image.setImageTintList(ColorStateList.valueOf(color));
    }


    protected void setBasicMenuColor(int color) {
        back_text.setTextColor(color);
        back_image.setImageTintList(ColorStateList.valueOf(color));
    }

    protected ImageView getMenuImageView() {
        return menu_image;
    }

    protected void setMeunText(String str) {
        menu_text.setText(str);
    }

    protected void setMenuImage(int res_id) {
        menu_image.setImageResource(res_id);
    }

    //初始化布局
    private void initView() {
        content_view = findViewById(R.id.content_view);
        View view = LayoutInflater.from(this).inflate(onCreateLayout(), null, false);
        ViewGroup.LayoutParams layoutParams = content_view.getLayoutParams();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        content_view.setLayoutParams(layoutParams);
        content_view.addView(view);
    }

    //初始化默认头布局
    private void initTopView() {
        toproot_view = findViewById(R.id.toproot_view);
        refreshLayout = findViewById(R.id.smartrefresh);
        refreshHeader = new MyRefreshHeader(this, MyRefreshHeader.TYPE_OTHER);
        refreshLayout.setRefreshHeader(refreshHeader);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                if (onRefreshCallback != null) {
                    onRefreshCallback.onRefresh();
                }
            }
        });
        setRefreshEnable(false);

        View view = LayoutInflater.from(this).inflate(R.layout.scorpio_layout_toolbar, toproot_view, false);
        view.findViewById(R.id.back_btn).setOnClickListener(this);
        view.findViewById(R.id.menu_btn).setOnClickListener(this);
        back_btn = view.findViewById(R.id.back_btn);
        menu_btn = view.findViewById(R.id.menu_btn);
        title_text = view.findViewById(R.id.title_text);
        back_text = view.findViewById(R.id.back_text);
        back_image = view.findViewById(R.id.back_image);
        menu_text = view.findViewById(R.id.menu_text);
        menu_image = view.findViewById(R.id.menu_image);
        toproot_view.addView(view);
        setTopLayoutParams(view);
    }

    public void setRefreshEnable(boolean b) {
        refreshLayout.setEnableRefresh(b);
    }

    //替换头布局
    protected void setTopView(int topViewlayoutId) {
        View topView = LayoutInflater.from(this).inflate(topViewlayoutId, toproot_view, false);
        for (int i = 0; i < toproot_view.getChildCount(); i++) {
            toproot_view.getChildAt(i).setVisibility(View.GONE);
        }
        toproot_view.addView(topView);
        setTopLayoutParams(topView);
    }

    //开始头布局
    private void setTopLayoutParams(final View topView) {
        toproot_view.post(new Runnable() {
            @Override
            public void run() {
                ViewGroup.LayoutParams layoutParams = toproot_view.getLayoutParams();
                layoutParams.height = topView.getHeight();
                layoutParams.width = topView.getWidth();
                toproot_view.setLayoutParams(layoutParams);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.back_btn) {
            finish();
        }
    }

    //将状态栏高度赋给basestatus_view
    private void resetStatusViewHeight() {
        basestatus_view = findViewById(R.id.basestatus_view);
        View baseroot_view = findViewById(R.id.baseroot_view);
        int statusheight = BarTextColorUtils.getStatusBarHeight(this);
        if (statusheight != -1) {
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
            basestatus_view.setLayoutParams(params);
            FrameLayout.LayoutParams fparams = (FrameLayout.LayoutParams) baseroot_view.getLayoutParams();
            fparams.topMargin = -statusheight;
            baseroot_view.setLayoutParams(fparams);
        }
    }

    //是否显示状态栏，默认显示
    protected void showStatusView(boolean show) {
        if (!show) basestatus_view.setVisibility(View.GONE);
        else basestatus_view.setVisibility(View.VISIBLE);
    }


    //隐藏头布局
    public void hideToolbarView() {
        toproot_view.setVisibility(View.GONE);
    }

    public void showToolbarView() {
        toproot_view.setVisibility(View.VISIBLE);
    }

    /*完成刷新*/
    public void finishRefresh(boolean isSuccess) {

        refreshHeader.finishUpdate(isSuccess);
        refreshLayout.finishRefresh();
    }

    public interface OnRefreshCallback {
        void onRefresh();
    }

    public void setOnRefreshCallback(OnRefreshCallback onRefreshCallback) {
        this.onRefreshCallback = onRefreshCallback;
    }

}
