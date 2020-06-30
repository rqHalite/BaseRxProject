package com.rock.baserxproject.http;

import com.rock.basemodel.http.Api;
import com.rock.basemodel.http.ApiServer;
import com.rock.basemodel.http.retrofit.nethelper.OkHttpClientHelper;
import com.rock.basemodel.http.retrofit.nethelper.gsonconverter.ResponseConverterFactory;
import com.rock.basemodel.http.retrofit.rx.RxRetrofitHelper;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * @author: ruan
 * @date: 2020/5/9
 * 重写helper,将网络请求回归到app
 */
public class RxAppRetrofitHelper {

    private static ApiServers helper;
    private Retrofit mRetrofit;
    private final OkHttpClient mClient;


    private RxAppRetrofitHelper() {
        mClient = OkHttpClientHelper.getInstance().getOkHttpClient();
    }

    /*
     *单例模式 对象唯一
     * 用于普通网络请求
     */
    public static ApiServers getInstance() {
        if (helper == null) {
            synchronized (RxAppRetrofitHelper.class) {
                if (helper == null) {
                    helper = new RxAppRetrofitHelper().getApi();
                }
            }
        }
        return helper;
    }

    public ApiServers getApi() {
        // 初始化Retrofit
        ApiServers apiUrl = getRetrofit().create(ApiServers.class);
        return apiUrl;
    }

    //构造Retrofit对象
    public Retrofit getRetrofit() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(Api.baseUrl)                                       //域名访问地址 这里只是为了方便demo单独写一个，最好的方式是在builderconfig里面配置，只要修改一下Build Varilant 就可以切换生产环境
                    .addConverterFactory(ResponseConverterFactory.create())    //在和后台配合开发的过程中 设计返回数据模型解决解析异常
//                      .addConverterFactory(JsonConverterEncryptionFactory.create())    // 数据加解密类型
//                      .addConverterFactory(GsonConverterFactory.create())      //1、转换器 添加gson支持      具体情况看后台的数据basemodle
//                      .addConverterFactory(FastJsonConverterFactory.create())  //2转换器 添加fastjason支持
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加RxJava支持
                    .client(mClient)                                           //关联ok3 设置client
                    .build();
        }
        return mRetrofit;
    }
}
