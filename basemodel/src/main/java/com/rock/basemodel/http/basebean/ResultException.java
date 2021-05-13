package com.rock.basemodel.http.basebean;

/**
 * time: 17/11/8 20:00
 * 用于捕获服务器约定的错误类型
 */
public class ResultException extends RuntimeException {
    private int errCode = 0;
    public String errmessgage;

    public ResultException(int errCode, String msg) {
        this.errCode = errCode;
        this.errmessgage = msg;
    }

}
