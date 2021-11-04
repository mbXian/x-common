package com.xmb.common.network;

import lombok.Data;

@Data
public class Result<T> {

    private static final String SUCCES = "SUCCES";
    private static final Integer SUCCES_CODE = 0;
    private static final String FAIL = "FAIL";
    private static final Integer FAIL_CODE = 500;
    /**
     * 0：成功；非0：失败
     */
    private int code;

    /**
     * 结果描述，一般成功时为空
     */
    private String msg;

    /**
     * restful返回结果
     */
    private T data;

    /**
     * 用户Id
     */
    private Long userId;

    private Result(Integer code, String msg, T data, Long userId) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.userId = userId;
    }

    public Result(Integer code, String msg, Long userId) {
        this.code = code;
        this.msg = msg;
        this.userId = userId;
    }

    public Result() {

    }

    /******* 成功 *******/
    public static Result ok(Long userId) {
        return new Result(SUCCES_CODE, SUCCES, userId);
    }

    public static Result ok(String msg, Long userId) {
        return new Result(SUCCES_CODE, msg, userId);
    }

    public static <T> Result ok(String msg, T data, Long userId) {
        return new Result(SUCCES_CODE, msg, data, userId);
    }

    public static <T> Result ok(T data, Long userId) {
        return new Result(SUCCES_CODE, SUCCES, data, userId);
    }

    /******* 失败 *******/
    public static Result fail(Long userId) {
        return new Result(FAIL_CODE, FAIL, userId);
    }

    public static Result fail(Integer code, String msg, Long userId) {
        return new Result(code, msg, userId);
    }

    public static Result fail(String msg, Long userId) {
        return new Result(FAIL_CODE, msg, userId);
    }

    public static <T> Result fail(String msg, T data, Long userId) {
        return new Result(FAIL_CODE, msg, data, userId);
    }

    public static <T> Result fail(T data, Long userId) {
        return new Result(FAIL_CODE, FAIL, data, userId);
    }


}
