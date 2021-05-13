package com.rock.baserxproject.view.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rock.basemodel.dialog.BaseDialog;
import com.rock.baserxproject.R;
import com.rock.baserxproject.utils.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * created by zhud on 2019/6/19
 */
public class ImagePagerDialog extends BaseDialog implements ViewPager.OnPageChangeListener {
    private ViewPager viewPager;
    private List<String> images = new ArrayList<>();
    private final TextView textView;

    public ImagePagerDialog(@NonNull Context context) {
        super(context, R.style.UniversalDialogStyle);
        initCenterMatchLayout();
        viewPager = findViewById(R.id.viewPager);
        textView = findViewById(R.id.image_flag);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(this);
        setCanceledOnTouchOutside(true);
    }

    public void show(int index, List<String> images) {
        this.images = images;
        adapter.notifyDataSetChanged();
        viewPager.setCurrentItem(index);

        textView.setText(String.format("%s/%s", "" + (index + 1), "" + images.size()));
        super.show();
    }

    @Override
    protected int onCreateLayout() {
        return R.layout.dialog_image_pager;
    }

    private PagerAdapter adapter = new PagerAdapter() {
        @Override
        public int getCount() {
            return images.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            PhotoView view = new PhotoView(mContext);
            view.enable();
//            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
            container.addView(view);
//            GlideUtils.loadImage(mContext, images.get(position), view);
//            Glide.with(mContext)
//                    .load(images.get(position))
//                    .diskCacheStrategy(DiskCacheStrategy.SOURCE)
////                    .placeholder(R.mipmap.ic_launcher)
//                    .into(view);
            ImageLoader.loadImage(mContext, images.get(position), view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    };

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int position) {
        textView.setText(String.format("%s/%s", "" + (position + 1), "" + images.size()));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
