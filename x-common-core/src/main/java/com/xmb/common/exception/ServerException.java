package com.xmb.common.exception;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Ben
 * @date 2020-05-06
 * @desc
 */
@Data
public class ServerException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final int PARAM_ERROR_CODE = 10001;
    public static final ServerException PARAM_ERROR = new ServerException(PARAM_ERROR_CODE, "参数错误！");
    public static final int PARSE_ERROR_CODE = 10002;
    public static final ServerException PARSE_ERROR = new ServerException(PARSE_ERROR_CODE, "解析错误！");
    public static final int AUTH_ERROR_CODE = 20001;
    public static final ServerException TOKEN_ERROR = new ServerException(AUTH_ERROR_CODE, "用户凭证错误！");
    public static final int WECHAT_API_ERROR_CODE = 30001;
    public static final ServerException WECHAT_API_ERROR = new ServerException(WECHAT_API_ERROR_CODE, "微信接口调用错误！");

    private String msg;
    private int code;

    public ServerException(int code, String msg) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public ServerException fmt(Object... args) {
        String message = this.msg;
        if (args != null && args.length > 0) {
            message = MessageFormat.format(message, args);
        }

        return new ServerException(this.code, message);
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap();
        map.put("className", ServerException.class.getName());
        map.put("code", this.code);
        map.put("msg", this.msg);
        return JSON.toJSONString(map);
    }
}
