package com.rock.basemodel.http.basebean;

/**
 * time: 17/11/8 10:19
 * 网络响应实体基类 -- 具体看情况
 */
public class BaseResponseBean<T> {
    public T data;
    public int code;
    public String errormessage;
    public boolean error;
    public T result;
}