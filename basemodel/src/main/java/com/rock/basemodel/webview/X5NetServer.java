package com.rock.basemodel.webview;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.rock.basemodel.BaseApplication;
import com.rock.basemodel.R;
import com.tencent.smtt.sdk.QbSdk;

/**
 * @author Ruanqi
 * @time 2019/8/15
 */
public class X5NetServer extends IntentService {
    public static final String CHANNEL_ID_STRING = "service_x5";
    public static final String TAG = "x5webview";
    public X5NetServer() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        initX5Web();
    }

    public void initX5Web() {
        if (!QbSdk.isTbsCoreInited()) {
            // 设置X5初始化完成的回调接口
            QbSdk.preInit(getApplicationContext(), null);
        }
        QbSdk.initX5Environment(getApplicationContext(), cb);
    }

    QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {
        @Override
        public void onViewInitFinished(boolean arg0) {
            // TODO Auto-generated method stub
            //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。
                Log.i("app", " onViewInitFinished is  -------------------------------------------" + arg0);
        }
        @Override
        public void onCoreInitFinished() {
            // TODO Auto-generated method stub
            Log.i("app", "onCoreInitFinished  -------------------------------------------");
        }
    };

    /**
     *为了防止8.0以上系统
     *IllegalStateException: Not allowed to start service Intent
     */
    @Override
    public void onCreate() {
        super.onCreate();
        //适配8.0service
        NotificationManager notificationManager = (NotificationManager) BaseApplication.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel mChannel = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            mChannel = new NotificationChannel(CHANNEL_ID_STRING, getString(R.string.app_name),
                    NotificationManager.IMPORTANCE_LOW);
            notificationManager.createNotificationChannel(mChannel);
            Notification notification = new Notification.Builder(getApplicationContext(), CHANNEL_ID_STRING).build();
            startForeground(1, notification);
        }
    }
}
