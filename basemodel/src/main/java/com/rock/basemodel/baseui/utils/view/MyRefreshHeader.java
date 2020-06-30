package com.rock.basemodel.baseui.utils.view;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.R;
import com.rock.basemodel.screentools.ScreenAdapterTools;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by 10300 on 2017/10/18.
 */

public class MyRefreshHeader extends LinearLayout implements RefreshHeader {

    View contentView;
    private TextView title;
    private ImageView arrow, loading;
    protected SpinnerStyle mSpinnerStyle = SpinnerStyle.Translate;
    private int type;
    public static final int TYPE_HOME = 0, TYPE_TRIP = 1, TYPE_OTHER = -1;//用于区分加载完成后的显示

    public MyRefreshHeader(Context context, int type) {
        super(context);

        this.type = type;
        initView(context);
    }

    private void initView(Context context) {

        contentView = LayoutInflater.from(context).inflate(R.layout.item_refresh_header, null);
        title = (TextView) contentView.findViewById(R.id.item_refresh_state);
        arrow = (ImageView) contentView.findViewById(R.id.item_refresh_arrow);
        loading = (ImageView) contentView.findViewById(R.id.item_refresh_loading);
        ScreenAdapterTools.getInstance().loadView(contentView);
        this.addView(contentView);
        this.setGravity(Gravity.CENTER);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
        } else {
            setPadding(getPaddingLeft(), 20, getPaddingRight(), 20);
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return mSpinnerStyle;
    }

    @Override
    public void setPrimaryColors(@ColorInt int... colors) {
    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

        arrow.setImageResource(R.mipmap.ic_down);
        title.setText(getResources().getString(R.string.pullToRefresh));
    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {

    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        return 500;
    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {

        switch (newState) {
            case None:
//                restoreRefreshLayoutBackground();
//                mLastUpdateText.setVisibility(mEnableLastTime ? VISIBLE : GONE);
                arrow.setImageResource(R.mipmap.ic_down);

            case PullDownToRefresh:
//                mTitleText.setText(REFRESH_HEADER_PULLDOWN);
//                mArrowView.setVisibility(VISIBLE);
//                mProgressView.setVisibility(GONE);
//                loading.setVisibility(GONE);
                arrow.setImageResource(R.mipmap.ic_down);
                arrow.animate().rotation(0);
                title.setText(getResources().getString(R.string.pullToRefresh));
//                mArrowView.animate().rotation(0);
                break;

            case Refreshing:
//                mTitleText.setText(REFRESH_HEADER_REFRESHING);
//                mProgressView.setVisibility(VISIBLE);
//                mArrowView.setVisibility(GONE);

//                arrow.setImageResource(R.mipmap.loading);
//                arrow.animate().rotation(0);
//                arrow.setVisibility(GONE);
                arrow.animate().rotation(0);
//                loading.setVisibility(VISIBLE);
                loading.setAnimation(AnimationUtils.loadAnimation(BaseApplication.getInstance(), R.anim.log_rotate));
                title.setText(getResources().getString(R.string.loading));
                break;

            case ReleaseToRefresh:

                arrow.animate().rotation(180);
                title.setText(getResources().getString(R.string.releaseToUpdate));
                break;

            case Loading:

                break;

            case RefreshFinish:

                if (type == TYPE_HOME) {
//                    ALog.i("RefreshHeader","加载完成");
//                    boolean getCarState = CarStateManager.getInstance().getCarState;
//                    String result = getCarState?"加载成功":"加载失败";
//                    String updateTime = CarStateManager.getInstance().updateTime;
//                    if (!TextUtils.isEmpty(updateTime)){
//
//                        result = result +" "+updateTime;
//                    }
//                    setUpdateResult(result,getCarState);

                } else if (type == TYPE_TRIP) {
//                    ALog.i("RefreshHeader","加载完成");
//                    boolean getQueryResponse = KeyshareImageUtils.getQueryResponse;
//                    setUpdateResult(getQueryResponse?"加载成功":"加载失败",getQueryResponse);
                } else if (type == TYPE_OTHER) {

//                    setUpdateResult("加载完成",true);
                }
                loading.clearAnimation();
//                loading.setVisibility(GONE);
//                arrow.setVisibility(VISIBLE);
                break;
        }
    }

    /*更新刷新的结果*/
    private void setUpdateResult(String result, boolean isSucceed) {

        title.setText(result);
        arrow.setImageResource(isSucceed ? R.mipmap.home_ic_succeed : R.mipmap.home_ic_warning);
    }

    /*供外部调用，显示刷新的结果*/
    public void finishUpdate(boolean isSucceed) {

        title.setText(isSucceed ? "加载成功" : "加载失败");
    }

    /*显示时间的刷新结果*/
    public void finishUpdateWithTime(boolean isSucceed, String time) {

        String flag = isSucceed ? "加载成功 " : "加载失败 ";
        String result = flag + time;
        title.setText(result);
    }
}
