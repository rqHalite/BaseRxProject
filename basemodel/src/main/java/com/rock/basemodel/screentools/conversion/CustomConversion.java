package com.rock.basemodel.screentools.conversion;

import android.view.View;

import com.rock.basemodel.screentools.loadviewhelper.AbsLoadViewHelper;


/**
 * Created by yatoooon on 2018/2/6.
 */

public class CustomConversion implements IConversion {

    @Override
    public void transform(View view, AbsLoadViewHelper loadViewHelper) {
        if (view.getLayoutParams() != null) {
            loadViewHelper.loadWidthHeightFont(view);
            loadViewHelper.loadPadding(view);
            loadViewHelper.loadLayoutMargin(view);
            loadViewHelper.loadMaxWidthAndHeight(view);
            loadViewHelper.loadMinWidthAndHeight(view);
        }
    }

}
