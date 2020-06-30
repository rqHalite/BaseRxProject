package com.rock.basemodel.http.retrofit.loadfile;

import io.reactivex.observers.DisposableObserver;

/**
 * @author: ruan
 * @date: 2020/4/14
 * 定义一个观察者
 */
public class ProgressDownSubscriber<T> extends DisposableObserver<T> {

    public T downinfo;


    @Override
    public void onNext(T t) {
        this.downinfo = t;

    }


    @Override
    public void onError(Throwable t) {

    }

    @Override
    public void onComplete() {

    }
}