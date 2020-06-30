package com.rock.basemodel.baseui.utils;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Ruanqi
 * @time 2019/7/29
 */
public class ActivityManagement {

    private static ActivityManagement instance;
    private Set<Activity> activityStack = new HashSet<>();
    private Activity mTopActivity = null;
    private int mActivityCount = 0;
    private boolean isHaveCackstageRecord = false;

    private ActivityManagement() {
    }

    public static synchronized ActivityManagement getInstance() {
        if (instance == null) {
            instance = new ActivityManagement();
        }
        return instance;
    }

    public void register(Application application) {
        application.registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }

    private Application.ActivityLifecycleCallbacks activityLifecycleCallbacks = new Application.ActivityLifecycleCallbacks() {
        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            getInstance().addActivity(activity);
            mTopActivity = activity;
        }

        @Override
        public void onActivityStarted(Activity activity) {
            mTopActivity = activity;
            mActivityCount++;
        }

        @Override
        public void onActivityResumed(Activity activity) {
            mTopActivity = activity;
        }

        @Override
        public void onActivityPaused(Activity activity) {

        }

        @Override
        public void onActivityStopped(Activity activity) {
            mActivityCount--;
            if (mActivityCount == 0) {
                isHaveCackstageRecord = true;
            }
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            getInstance().removeActivity(activity);
            if (mTopActivity == activity) mTopActivity = null;
        }
    };

    public synchronized void addActivity(Activity aty) {
        activityStack.add(aty);
    }

    public synchronized void removeActivity(Activity aty) {
        activityStack.remove(aty);
    }

    /**
     * 结束所有Activity
     */
    public synchronized void finishAllActivity() {
        for (Activity activity : activityStack) {
            activity.finish();
        }
        activityStack.clear();
    }

    /**
     * 清除除了指定activity以外的Activiti
     */
    public synchronized void finishExceptMyActivity(Activity view) {
        for (Activity avtivity : activityStack) {
            if (view == avtivity) continue;
            avtivity.finish();
        }
    }

    /**
     * 指定activity是否已经销毁
     *
     * @param act true/已销毁
     * @return
     */
    public synchronized boolean activityIsDestroyed(Activity act) {
        for (Activity activity : activityStack) {
            if (act == activity) return false;
        }
        return true;
    }

    /**
     * 清楚除了指定class以外的activity
     *
     * @param aClass
     */
    public synchronized void finishExceptMyClass(Class aClass) {
        for (Activity avtivity : activityStack) {
            if (aClass == avtivity.getClass()) continue;
            avtivity.finish();
        }
    }

    public synchronized int getActivityStackCount() {
        return activityStack.size();
    }

    public boolean isHaveCackstageRecord() {
        boolean isHaveCackstageRecord = this.isHaveCackstageRecord;
        this.isHaveCackstageRecord = false;
        return isHaveCackstageRecord;
    }

    public Activity getTopActivity() {
        return mTopActivity;
    }

    public int getActivityCount() {
        return mActivityCount;
    }

}
