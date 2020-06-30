package com.rock.baserxproject.adapter;


import androidx.fragment.app.FragmentManager;

import com.rock.basemodel.baseui.adapter.BasicFragmentAdapter;
import com.trello.rxlifecycle2.components.support.RxFragment;
import com.trello.rxlifecycle2.components.support.RxFragmentActivity;

import java.util.List;

/**
 * @author Ruanqi
 * @time 2019/7/31
 */
public class HomeFragmentAdapter extends BasicFragmentAdapter<RxFragment> {
    public HomeFragmentAdapter(RxFragmentActivity activity) {
        super(activity);

    }

    @Override
    protected void init(FragmentManager manager, List<RxFragment> list) {
//        list.add(MainFragment.newInstance());
//        list.add(MessageFragment.newInstance());
//        list.add(MainFragment.newInstance());
//        list.add(MineFragment.newInstance());
    }
}
