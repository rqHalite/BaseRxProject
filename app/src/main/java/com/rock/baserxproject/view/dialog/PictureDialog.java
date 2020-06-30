package com.rock.baserxproject.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.rock.basemodel.screentools.ScreenAdapterTools;
import com.rock.baserxproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author: ruan
 * @date: 2020/5/16
 */
public class PictureDialog extends Dialog {

    private final Context mContext;
    @BindView(R.id.photo_view)
    PhotoView photoView;
    @BindView(R.id.ft_images)
    FrameLayout ftImages;
    private View view;
    private final String mUrl;
    private final WindowManager windowManager;

    public PictureDialog(@NonNull Context context, String image) {
        super(context, R.style.MyDialog);
        mContext = context;
        mUrl = image;
        windowManager = ((Activity) context).getWindowManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = View.inflate(mContext,R.layout.item_dialog_layout, null);
        ScreenAdapterTools.getInstance().loadView(view);
        requestWindowFeature(Window.FEATURE_NO_TITLE);//去除屏幕的title
        setContentView(view);//设置diaolog的样式布局
        ButterKnife.bind(this, view);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);//设置dialog背景为透明色
        getWindow().setGravity(Gravity.CENTER);//设置dialog的位置
        Point point = new Point();
        windowManager.getDefaultDisplay().getSize(point);//获取屏幕的宽和高
        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();//获取弹框当前的参数
        layoutParams.width = (int) (point.x * 1);
        setCanceledOnTouchOutside(true);//设置点击屏幕dialog不消失
        onWindowAttributesChanged(layoutParams);  //改变dialog窗口的位置
        getWindow().setAttributes(layoutParams);
        Glide.with(mContext).load(mUrl).into(photoView);
        photoView.enable();
    }

    @OnClick(R.id.ft_images)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ft_images:
                dismiss();
                break;
        }
    }

}
