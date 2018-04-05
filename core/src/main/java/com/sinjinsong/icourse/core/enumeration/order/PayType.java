package com.sinjinsong.icourse.core.enumeration.order;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public enum PayType {
    ONLINE(0,"线上支付"),OFFLINE(1,"线下支付");
    
    private int code;
    private String desc;

    PayType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
    
    private static Map<Integer, PayType> map = new HashMap<>();

    static {
        for (PayType status : values()) {
            map.put(status.code, status);
        }
    }

    public static PayType getByCode(int code) {
        return map.get(code);
    }
}
