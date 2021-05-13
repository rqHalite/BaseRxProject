package com.rock.baserxproject.http;

import com.rock.basemodel.http.ApiServer;
import com.rock.basemodel.http.retrofit.rx.BaseReponse;
import com.rock.baserxproject.bean.HttpBean;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Ruanqi
 * @time 2019/2/27
 */
public interface ApiServers {


    @GET("/api/v2/data/category/GanHuo/type/{type}/page/{page}/count/{count}")
    Observable<HttpBean> getTest(@Path("type") String type,
                                 @Path("page") String page,
                                 @Path("count") String count);

    @GET("/api/v2/hot/likes/category/Girl/count/{count}")
    Observable<HttpBean> getPic(@Path("count") String count);

}
