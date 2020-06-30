package com.rock.basemodel.http.retrofit.loadfile;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * @author: ruan
 * @date: 2020/4/14
 * 自定义下载拦截器
 */
public class Downloadinterceptor  implements Interceptor {
    private DownFileCallback downFileCallback;

    private String downUrl;

    public Downloadinterceptor(DownFileCallback listener,String downUrl) {
        this.downFileCallback = listener;
        this.downUrl = downUrl;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        return response.newBuilder()
                .body(new DownloadResponseBody(response.body(), downFileCallback,downUrl))
                .build();
    }
}
