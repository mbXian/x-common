package com.xmb.common.utils;

/**
 * @author Ben Xian
 * @email 18824140606@163.com
 * @create 2023-06-01 17:46
 */
public class StringUtil {

    /**
     * 是否数字
     */
    public static boolean isNumeric(String str){
        try {
            long result = Long.valueOf(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
