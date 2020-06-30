package com.rock.basemodel.http.retrofit.loadfile;


import com.rock.basemodel.http.basebean.DownloadInfo;

/**
 * @author: ruan
 * @date: 2020/4/14
 * 设置完成回调
 */
public interface DownFileCallback {

    void onSuccess(DownloadInfo info);

    void onFail(String msg);

    void onProgress(long totalSize, long downSize);
}
