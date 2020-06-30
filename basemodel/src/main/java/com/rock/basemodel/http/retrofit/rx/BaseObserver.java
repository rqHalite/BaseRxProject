package com.rock.basemodel.http.retrofit.rx;

import android.util.Log;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 *基类数据不做统一处理
 * @param <T>
 */
public abstract class BaseObserver<T> implements Observer<T> {
    private static final String TAG = "BaseObserver";

    @Override
    public void onSubscribe(Disposable d) {
        Log.i(TAG, "onSubscribe: ");
    }

    @Override
    public void onNext(T t) {
        //直接返回基类请求数据，不做预处理
        onSuccess(t);
    }
    @Override
    public void onError(Throwable e) {
        Log.i(TAG, "Throwable: " + e.getMessage());
        onFailure(null, e.getMessage());
    }

    @Override
    public void onComplete() {
        Log.i(TAG, "onComplete: ");
    }

    public abstract void onSuccess(T demo);

    public abstract void onFailure(Throwable e, String errorMsg);
}

/**
 * 对基类数据提前预处理
 * @param <T>
 */
//public abstract class BaseObserver<T> implements Observer<BaseReponse<T>> {
//    private static final String TAG = "BaseObserver";
//
//    @Override
//    public void onSubscribe(Disposable d) {
//        Log.i(TAG, "onSubscribe: ");
//    }
//
//    @Override
//    public void onNext(BaseReponse<T> response) {
//        //在这边对 基础数据 进行统一处理  举个例子：
//            if(response.code==200){
//                onSuccess(response.demo);
//            }else{
//                onFailure(null,response.msg);
//            }
//    }
//
//    @Override
//    public void onError(Throwable e) {
//        Log.i(TAG, "Throwable: " + e.getMessage());
//    }
//
//    @Override
//    public void onComplete() {
//        Log.i(TAG, "onComplete: ");
//    }
//
//    public abstract void onSuccess(T demo);
//
//    public abstract void onFailure(Throwable e, String errorMsg);
//}
