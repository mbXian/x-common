package com.xmb.common.exception;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final BizException UNKONW_EXCEPTION = new BizException(99999999, "系统不舒服");
    public static final BizException PARAM_CHECK_EXCEPTION = new BizException(99999901, "参数校验失败");
    public static final BizException ERROR_CHAR_EXCEPTION = new BizException(99999902, "包含非法字符");
    public static final BizException PARAM_GET_EXCEPTION = new BizException(99999903, "获取参数失败");
    public static final BizException FEIGN_CAUSE_EXCEPTION = new BizException(99990004, "系统不舒服！");
    public static final BizException ENUM_METHOD_FAIL = new BizException(99990005, "获取方法失败：{}.{}");
    public static final BizException NOT_FOUND_EXCEPTION = new BizException(99990006, "接口未找到：{}.{}");
    public static final BizException TIMEOUT_EXCEPTION = new BizException(99990007, "接口响应超时：{}.{}");
    private String msg;
    private int code;

    public BizException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public BizException fmt(Object... args) {
        String message = this.msg;
        if (args != null && args.length > 0) {
            message = MessageFormat.format(message, args);
        }

        return new BizException(this.code, message);
    }

    public String getMsg() {
        return this.msg;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap();
        map.put("className", BizException.class.getName());
        map.put("code", this.code);
        map.put("msg", this.msg);
        return JSON.toJSONString(map);
    }
}
