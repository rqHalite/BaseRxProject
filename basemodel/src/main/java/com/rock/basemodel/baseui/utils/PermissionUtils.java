package com.rock.basemodel.baseui.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.trello.rxlifecycle2.components.support.RxFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ruanqi
 * @time 2019/7/29
 */
public class PermissionUtils {
    private static final String CHECK_OP_NO_THROW = "checkOpNoThrow";
    private static final String OP_POST_NOTIFICATION = "OP_POST_NOTIFICATION";

    /**
     * 判断是否有指定的权限
     */
    public static boolean hasPermission(Context context, String... permissions) {
        for (String permisson : permissions) {
            if (ContextCompat.checkSelfPermission(context, permisson) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    /**
     * 检查权限 并且打开
     *
     * @param activity
     * @param check_permissions SDK_INT 6.0 23         该版本以上检查权限
     * @param requestCode       设置完毕返回码
     * @return true 表示已有 所查权限
     */
    public static boolean hasPermissionAndOpen(Activity activity, List<String> check_permissions, int requestCode) {
        if (Build.VERSION.SDK_INT >= 23) {
            List<String> permissions = new ArrayList<>();
            for (int i = 0; i < check_permissions.size(); i++) {
                String permission = check_permissions.get(i);
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(activity, permission)) {
                    permissions.add(permission);
                }
            }
            if (permissions.size() != 0) {
                ActivityCompat.requestPermissions(activity, permissions.toArray(new String[0]), requestCode);
                return false;
            }
        }
        return true;
    }

    /**
     * 检查权限 并且打开
     *
     * @param fragment
     * @param check_permissions
     * @param sdk_int           该版本以上检查权限
     * @param requestCode       设置完毕返回码
     * @return true 表示已有 所查权限
     */
    public static boolean hasPermissionAndOpen(RxFragment fragment, List<String> check_permissions, int sdk_int, int requestCode) {
        if (Build.VERSION.SDK_INT >= sdk_int) {
            List<String> permissions = new ArrayList<>();
            for (int i = 0; i < check_permissions.size(); i++) {
                String permission = check_permissions.get(i);
                if (PackageManager.PERMISSION_GRANTED != ActivityCompat.checkSelfPermission(fragment.getContext(), permission)) {
                    permissions.add(permission);
                }
            }
            if (permissions.size() != 0) {
                fragment.requestPermissions(permissions.toArray(new String[0]), requestCode);
                return false;
            }
        }
        return true;
    }

    /**
     * 是否开启通知栏权限
     **/
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public static boolean isNotificationEnabled(Context context) {
        AppOpsManager mAppOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        ApplicationInfo appInfo = context.getApplicationInfo();
        String pkg = context.getApplicationContext().getPackageName();
        int uid = appInfo.uid;
        Class appOpsClass;
        /* Context.APP_OPS_MANAGER */
        try {
            appOpsClass = Class.forName(AppOpsManager.class.getName());
            Method checkOpNoThrowMethod = appOpsClass.getMethod(CHECK_OP_NO_THROW, Integer.TYPE, Integer.TYPE, String.class);
            Field opPostNotificationValue = appOpsClass.getDeclaredField(OP_POST_NOTIFICATION);
            int value = (Integer) opPostNotificationValue.get(Integer.class);
            return ((Integer) checkOpNoThrowMethod.invoke(mAppOps, value, uid, pkg) == AppOpsManager.MODE_ALLOWED);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据手机品牌跳转到权限开启界面
     */
    public static void toGrantPage(Context context) {
        Activity activity = (Activity) context;
        Intent intent = new Intent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts("package", activity.getPackageName(), null));
        activity.startActivity(intent);
        activity.finish();
    }
}

