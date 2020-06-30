package com.rock.basemodel.http;


import com.rock.basemodel.http.retrofit.rx.BaseReponse;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public abstract interface ApiServer {

//    @GET("/v1/resource/search/{searchname}?suggest=true")
//    Observable<BaseReponse<SearchDataList>>
//    getSearchVideo(@Path("searchname") String searchname,
//                   @Query("rank") String rank,
//                   @Query("size") String size);
    @GET("/api/v2/data/category/GanHuo/type/{type}/page/{page}/count/{count}")
    Observable<BaseReponse> getTest(@Path("type") String type,
                                    @Path("page") String page,
                                    @Path("count") String count);
//
//
//    @GET("/")
//    Observable<BaseReponse<SearchDataList>>
//    getTest2(@QueryMap Map<String, String> map);


//    /**
//     * TODO Get请求
//     */
//    //第一种方式：GET不带参数
//    @GET("retrofit.txt")
//    Observable<SearchDataList> getUser();
//    @GET
//    Observable<SearchDataList> getUser(@Url String url);
//    @GET
//    Observable<SearchDataList> getUser1(@Url String url); //简洁方式   直接获取所需数据
//    //第二种方式：GET带参数
//    @GET("api/data/{type}/{count}/{page}")
//    Observable<SearchDataList> getUser(@Path("type") String type, @Path("count") int count, @Path("page") int page);
//    //第三种方式：GET带请求参数：https://api.github.com/users/whatever?client_id=xxxx&client_secret=yyyy
//    @GET("users/whatever")
//    Observable<SearchDataList> getUser(@Query("client_id") String id, @Query("client_secret") String secret);
//    @GET("users/whatever")
//    Observable<SearchDataList> getUser(@QueryMap Map<String, String> info);
//
//    /**
//     * TODO POST请求
//     */
//    //第一种方式：@Body
//    @Headers("Accept:application/json")
//    @POST("login")
//    Observable<SearchDataList> postUser(@Body RequestBody body);
//    //第二种方式：@Field
//
//    @Headers("Accept:application/json")
//    @POST("auth/login")
//    @FormUrlEncoded
//    Observable<SearchDataList> postUser(@Field("username") String username, @Field("password") String password);
//    //多个参数
//    Observable<SearchDataList> postUser(@FieldMap Map<String, String> map);
//
//    /**
//     * TODO DELETE
//     */
//    @DELETE("member_follow_member/{id}")
//    Observable<SearchDataList> delete(@Header("Authorization") String auth, @Path("id") int id);
//
//    /**
//     * TODO PUT
//     */
//    @PUT("member")
//    Observable<SearchDataList> put(@HeaderMap Map<String, String> headers,
//                         @Query("nickname") String nickname);
//
//    /**
//     * TODO 文件上传
//     */
//    @Multipart
//    @POST("upload")
//    Observable<ResponseBody> upload(@Part("description") RequestBody description, @Part MultipartBody.Part file);
//
//    //亲测可用
//    @Multipart
//    @POST("member/avatar")
//    Observable<SearchDataList> uploadImage(@HeaderMap Map<String, String> headers, @Part MultipartBody.Part file);
//
//    /**
//     * 多文件上传
//     */
//    @Multipart
//    @POST("register")
//    Observable<ResponseBody> upload(@PartMap Map<String, RequestBody> params, @Part("description") RequestBody description);
//    //Observable<ResponseBody> upload(@Part() List<MultipartBody.Part> parts);
//
//    @Multipart
//    @POST("member/avatar")
//    Observable<SearchDataList> uploadImage1(@HeaderMap Map<String, String> headers, @Part List<MultipartBody.Part> file);
//
//    /**
//     * 来自https://blog.csdn.net/impure/article/details/79658098
//     * @Streaming 这个注解必须添加，否则文件全部写入内存，文件过大会造成内存溢出
//     */
    @Streaming
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);

}
