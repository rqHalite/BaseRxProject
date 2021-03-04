package com.rock.baserxproject.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.rock.basemodel.baseui.adapter.BasicQuickAdapter;
import com.rock.basemodel.baseui.adapter.BasicViewHolder;
import com.rock.baserxproject.R;
import com.rock.baserxproject.bean.HttpBean;
import com.rock.baserxproject.bean.PictureListBean;
import com.rock.baserxproject.utils.ImageLoader;
import com.rock.baserxproject.view.ScaleImageView;

import java.util.List;


/**
 * @author: ruan
 * @date: 2020/5/15
 */
public class PicListAdapter extends BasicQuickAdapter<HttpBean.DataBean, BasicViewHolder> {

    public PicListAdapter(@Nullable List data) {
        super(R.layout.item_picture_layout, data);
    }

    @Override
    protected void convert(BasicViewHolder helper, HttpBean.DataBean item) {
        super.convert(helper, item);
        ScaleImageView imageView = helper.getView(R.id.pic_image);
        imageView.setInitSize(item.getWidth(), item.getHight());

        ImageLoader.loadLargeImage(mContext,item.getUrl(),helper.getView(R.id.pic_image));
        helper.addOnClickListener(R.id.pic_image);
    }
}
