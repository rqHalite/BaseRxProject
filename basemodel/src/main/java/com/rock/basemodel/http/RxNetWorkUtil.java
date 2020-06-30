package com.rock.basemodel.http;

import com.rock.basemodel.http.retrofit.rx.MyObserver;
import com.rock.basemodel.http.retrofit.rx.RxHelper;
import com.rock.basemodel.http.retrofit.rx.RxRetrofitHelper;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;
import com.trello.rxlifecycle2.components.support.RxFragment;

import java.util.Map;

public class RxNetWorkUtil {

    public static void getTestList(RxFragment activity, Map<String,String> map, MyObserver observer) {
        RxRetrofitHelper.getInstance().
                getTest(map.get("type"),map.get("page"),map.get("count")).compose(RxHelper.observableIO2Main(activity))
                .subscribe(observer);
    }
//
//    public static void getTestList2(RxAppCompatActivity activity, Map<String, String> map, MyObserver observer) {
//        RxRetrofitHelper.getInstance().
//                getTest2(map).compose(RxHelper.observableIO2Main(activity))
//                .subscribe(observer);
//    }

//    public static void downLoad(RxAppCompatActivity activity, Map<String, String> map, MyObserver observer) {
//        RxRetrofitHelper.getInstance().
//                download(map.get("start"), map.get("url")).compose(RxHelper.observableIO2Main(activity))
//                .subscribe(observer);
//    }
}
