package com.rock.baserxproject.ui.fragment;


import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.rock.baserxproject.R;
import com.rock.baserxproject.base.MyFragment;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class MineFragment extends MyFragment {

    @BindView(R.id.status_bar_fix)
    View mStateBarFixer;
    @BindView(R.id.back)
    ImageView back;
    Unbinder unbinder;
    private ArgbEvaluator evaluator;

    public static MineFragment newInstance() {
        // Required empty public constructor
        MineFragment fragment = new MineFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initData() {
//        View mStateBarFixer = mView.findViewById(R.id.status_bar_fix);
//        mStateBarFixer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
//                getStateBarHeight(getActivity())));//填充状态栏
//
//        evaluator = new ArgbEvaluator();
//        int evaluate = (Integer) evaluator.evaluate(0, 0x00000000, 0XFFE93030);
//        mStateBarFixer.setBackgroundColor(evaluate);//状态栏填充布局也要更改颜色

//        basestatus_view = findViewById(R.id.basestatus_view);
//        View baseroot_view = findViewById(R.id.baseroot_view);
//        int statusheight = BarTextColorUtils.getStatusBarHeight(this);
//        if (statusheight != -1) {
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusheight);
//            basestatus_view.setLayoutParams(params);
//            FrameLayout.LayoutParams fparams = (FrameLayout.LayoutParams) baseroot_view.getLayoutParams();
//            fparams.topMargin = -statusheight;
//            baseroot_view.setLayoutParams(fparams);
//        }
    }


    /**
     * 获取状态栏高度,在页面还没有显示出来之前
     *
     * @param a
     * @return
     */
    public static int getStateBarHeight(Activity a) {
        int result = 0;
        int resourceId = a.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resourceId > 0) {
            result = a.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
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


    @OnClick({R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back:
                break;
        }
    }
}
