package com.rock.basemodel.http.retrofit.rx;


import com.rock.basemodel.http.Api;
import com.rock.basemodel.http.ApiServer;
import com.rock.basemodel.http.retrofit.nethelper.OkHttpClientHelper;
import com.rock.basemodel.http.retrofit.nethelper.gsonconverter.ResponseConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

public class RxRetrofitHelper {
    private static ApiServer helper;
    private Retrofit mRetrofit;
    private final OkHttpClient mClient;


    private RxRetrofitHelper() {
        mClient = OkHttpClientHelper.getInstance().getOkHttpClient();
    }

    /*
     *单例模式 对象唯一
     * 用于普通网络请求
     */
    public static ApiServer getInstance() {
        if (helper == null) {
            synchronized (RxRetrofitHelper.class) {
                if (helper == null) {
                    helper = new RxRetrofitHelper().getApi();
                }
            }
        }
        return helper;
    }

    public ApiServer getApi() {
// 初始化Retrofit
        ApiServer apiUrl = getRetrofit().create(ApiServer.class);
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

// ------------------------------------------- 以下方法用于加载大文件-------------------------------------------------
//
//    /*
//         用于下载大文件
//     * */
//    public static ApiServer getLoadInstance() {
//        if (helper == null) {
//            synchronized (RxRetrofitHelper.class) {
//                if (helper == null) {
//                    helper = new RxRetrofitHelper().getLoadFileApi();
//                }
//            }
//        }
//        return helper;
//    }
//
//    public ApiServer getLoadFileApi() {
//// 初始化Retrofit
//        ApiServer apiUrl = getLoadFileRetrofit().create(ApiServer.class);
//        return apiUrl;
//    }
//
//
//    //构造Retrofit对象
//    public Retrofit getLoadFileRetrofit() {
//        ExecutorService executorService = Executors.newFixedThreadPool(1);
//        //特别注意下载大文件时千万不要打开下面注释代码 否则会导致OOM
//        //.addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
//        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()//
//                .readTimeout(5, TimeUnit.SECONDS)//
//                .connectTimeout(5, TimeUnit.SECONDS)//
//                .addInterceptor(new DownloadProgressInterceptor())
//                .build();
//
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://f5.market.xiaomi.com/download/AppStore/")
//                .client(okHttpClient)
//                .addConverterFactory(ResponseConverterFactory.create())
//                .callbackExecutor(executorService) //默认CallBack回调在主线程进行,当设置下载大文件时需设置注解@Stream 不加这句话会报android.os.NetworkOnMainThreadException
//                .build();
//        if (mRetrofit == null) {
//            mRetrofit = new Retrofit.Builder()
//                    .baseUrl(Api.baseUrl)                                       //域名访问地址 这里只是为了方便demo单独写一个，最好的方式是在builderconfig里面配置，只要修改一下Build Varilant 就可以切换生产环境
//                    .addConverterFactory(ResponseConverterFactory.create())    //在和后台配合开发的过程中 设计返回数据模型解决解析异常
////                      .addConverterFactory(JsonConverterEncryptionFactory.create())    // 数据加解密类型
////                      .addConverterFactory(GsonConverterFactory.create())      //1、转换器 添加gson支持      具体情况看后台的数据basemodle
////                      .addConverterFactory(FastJsonConverterFactory.create())  //2转换器 添加fastjason支持
//                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())  //添加RxJava支持
//                    .client(okHttpClient)                                           //关联ok3 设置client
//                    .callbackExecutor(executorService)
//                    .build();
//        }
//        return mRetrofit;
//    }

}
