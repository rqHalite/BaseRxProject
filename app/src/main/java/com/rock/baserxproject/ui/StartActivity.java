package com.rock.baserxproject.ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.hjq.toast.ToastUtils;
import com.rock.basemodel.baseui.utils.PermissionUtils;
import com.rock.baserxproject.R;
import com.rock.baserxproject.base.MyActivity;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends MyActivity {
    @Override
    protected int onCreateLayout() {
        return R.layout.activity_start;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        hideToolbarView();
        showStatusView(false);
        checkPermissions();

    }

    private void checkPermissions() {
        List<String> permissions = new ArrayList<>();
        permissions.add(Manifest.permission.READ_EXTERNAL_STORAGE);
        permissions.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (PermissionUtils.hasPermissionAndOpen(this, permissions, 0)) {
            skipActivity(MainAddActivity.class);
            finish();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//同意权限
                try {
                    Thread.sleep(3000);
                    skipActivity(MainAddActivity.class);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {//拒绝权限
                ToastUtils.show("你拒绝了读写权限");
            }
        }
    }
}
