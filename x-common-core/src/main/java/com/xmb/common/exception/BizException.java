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
public class BizException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final BizException PARAM_ERROR = new BizException(10001, "参数错误！");

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

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap();
        map.put("className", BizException.class.getName());
        map.put("code", this.code);
        map.put("msg", this.msg);
        return JSON.toJSONString(map);
    }
}
