package com.rock.basemodel.statusbars;

import android.view.Window;

/**
 * 状态栏接口
 */

interface IStatusBar {
    /**
     * Set the status bar color
     *
     * @param window The window to set the status bar color
     * @param color  Color value
     */
    void setStatusBarColor(Window window, int color);
}
