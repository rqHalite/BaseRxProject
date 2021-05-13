package com.rock.baserxproject.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.rock.baserxproject.R;

/**
 * @author: ruan
 * @date: 2020/5/16
 */
public class ImageLoader {

    public static void load(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white)
                .thumbnail(0.5f)
                .into(iv);
    }

    public static void load(Context context, String url, ImageView iv, int placeholder) {
        Glide.with(context)
                .load(url)
                .diskCacheStrategy(DiskCacheStrategy.ALL)//让Glide既缓存全尺寸图片，下次在任何ImageView中加载图片的时候，全尺寸的图片将从缓存中取出，重新调整大小，然后缓存
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white)
                .thumbnail(0.5f)
                .placeholder(placeholder)
                .into(iv);
    }

    public static void load(Context context, int resId, ImageView iv) {
        Glide.with(context)
                .load(resId)
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white)
                .thumbnail(0.5f)
                .into(iv);
    }

    /**
     * 需要在子线程执行
     *
     * @param context
     * @param url
     * @return
     */
    public static Bitmap load(Context context, String url) {
        try {
            return Glide.with(context)
                    .asBitmap()
                    .load(url)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 加载普通图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadImage(Context context, String url, ImageView imageView) {
        Glide
                .with(context)
                .load(url)
                .skipMemoryCache(false)
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white)
                .thumbnail(0.5f)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .dontAnimate()
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        imageView.setImageDrawable(resource);
                    }
                });

    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadRoundImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
//                .placeholder(R.mipmap.ic_upload_images)
                .centerCrop()
                .placeholder(R.color.color_f8f8f8) //加载成功前显示的图片
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white)
                .fallback(new ColorDrawable(Color.GRAY))
                .override(300, 300)
                .thumbnail(0.1f)
                .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                .into(imageView);

    }

    /**
     * 加载带圆角图片
     *
     * @param context
     * @param path
     * @param imageView
     * @param corner
     */
    public static void loadRoundImageWithCorner(Context context, String path, ImageView imageView, int corner) {
        RequestOptions options = new RequestOptions()
                .placeholder(R.color.color_f8f8f8) //加载成功前显示的图片
                .fallback(R.color.color_white) //url为空的时候,显示的图片
                .error(R.color.color_white);//图片加载失败后，显示的图片

        Glide.with(context)
//                .asBitmap()
                .load(path)
                .dontAnimate()
                .skipMemoryCache(false)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(options)
                .dontAnimate()
                //解决imageview设置centerCrop导致圆角设置失败的问题
                .transform(new CenterCrop(), new CircleTransform(context, corner))
                .into(imageView);
    }

    /**
     * 根据款自适应高
     *
     * @param context
     * @param path
     * @param imageView
     */
    public static void loadLargeImage(Context context, String path, ImageView imageView) {

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();

        int height = wm.getDefaultDisplay().getHeight();
        Log.e("aa", "---控件宽---->>>" + width + "-------控件高------>>>" + height);
        ViewGroup.LayoutParams params1 = imageView.getLayoutParams();
        params1.width = width;
        imageView.setLayoutParams(params1);
        RequestOptions requestOptions = new RequestOptions();
        Glide
                .with(context)
                .load(path)
                .thumbnail(0.1f)
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        //这里有一个问题当你第一次调用时ImageView.getWidth为0，不能直接采用imageView.getWidth()方法
                        final ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        float scale = (float) width / (float) resource.getIntrinsicWidth();

                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
//                        Log.e("aa", "-----图片自高--->>>" + vh + "-----离顶部高度--->>>" + imageView.getPaddingTop() + "-----离底部高度--->>>" + imageView.getPaddingBottom());
                        params.height = vh;
                        params.width = width;
                        imageView.setLayoutParams(params);
                        ViewTreeObserver viewTreeObserver = imageView.getViewTreeObserver();
                        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                            @Override
                            public void onGlobalLayout() {

                                int vw = imageView.getWidth();
//                                Log.e("aa", vw + "------------->>>" + imageView.getPaddingLeft() + "------------>>>" + imageView.getPaddingRight());
                                float scale = (float) vw / (float) resource.getIntrinsicWidth();

                                int vh = Math.round(resource.getIntrinsicHeight() * scale);
                                Log.e("aa", "-----图片自高--->>>" + vh + "-----离顶部高度--->>>" + imageView.getPaddingTop() + "-----离底部高度--->>>" + imageView.getPaddingBottom());
                                params.height = vh;
                                params.width = width;
                                imageView.setLayoutParams(params);
                                imageView.getViewTreeObserver()
                                        .removeGlobalOnLayoutListener(this);
                            }
                        });

                        return false;
                    }
                }).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                //加载图片
                imageView.setImageDrawable(resource);
            }
        });

    }
}
