package com.rock.baserxproject.adapter;


import androidx.annotation.Nullable;

import com.rock.basemodel.baseui.adapter.BasicQuickAdapter;
import com.rock.basemodel.baseui.adapter.BasicViewHolder;
import com.rock.baserxproject.R;
import com.rock.baserxproject.bean.HttpBean;

import java.util.List;

/**
 * @author Ruanqi
 * @time 2019/7/30
 */
public class MainAdapter extends BasicQuickAdapter<HttpBean.DataBean, BasicViewHolder> {
    public MainAdapter(@Nullable List data) {
        super(R.layout.item_main_layout, data);
    }

    @Override
    protected void convert(BasicViewHolder helper, HttpBean.DataBean item) {
        super.convert(helper, item);
        helper.addOnClickListener(R.id.list_item_layout).setText(R.id.item_text, item.getDesc());
    }
}
