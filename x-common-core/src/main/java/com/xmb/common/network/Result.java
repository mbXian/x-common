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

	private Result(Integer code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Result(Integer code, String msg){
		this.code = code;
		this.msg = msg;
	}
	public Result( ){

	}

	/******* 成功 *******/
	public static Result ok() {
		return new Result(SUCCES_CODE,SUCCES,null);
	}

	public static Result ok(String msg) {
		return new Result(SUCCES_CODE,msg,null);
	}

	public static <T> Result ok(String msg, T data) {
		return new Result(SUCCES_CODE,msg,data);
	}

	public static <T> Result ok(T data) {
		return new Result(SUCCES_CODE,SUCCES,data);
	}

	/******* 失败 *******/
	public static Result fail() {
		return new Result(FAIL_CODE,FAIL,null);
	}

	public static Result fail(Integer code, String msg) {
		return new Result(code,msg,null);
	}

	public static Result fail(String msg) {
		return new Result(FAIL_CODE,msg,null);
	}

	public static <T> Result fail(String msg, T data) {
		return new Result(FAIL_CODE,msg,data);
	}

	public static <T> Result fail(T data) {
		return new Result(FAIL_CODE,FAIL,data);
	}
	

}
