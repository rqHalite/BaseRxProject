package com.rock.baserxproject.http;

import com.rock.basemodel.http.retrofit.rx.MyObserver;
import com.rock.basemodel.http.retrofit.rx.RxHelper;
import com.rock.basemodel.http.retrofit.rx.RxRetrofitHelper;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

/**
 * @author: ruan
 * @date: 2020/5/9
 */
public class RxAppNetWorkUtils {

    public static void getTestList(RxFragment activity, Map<String,String> map, MyObserver observer) {
        RxAppRetrofitHelper.getInstance().
                getTest(map.get("type"),map.get("page"),map.get("count")).compose(RxHelper.observableIO2Main(activity))
                .subscribe(observer);
    }
    public static void getPicList(RxFragment activity, Map<String,String> map, MyObserver observer) {
        RxAppRetrofitHelper.getInstance().
                getPic(map.get("count")).compose(RxHelper.observableIO2Main(activity))
                .subscribe(observer);
    }
}
