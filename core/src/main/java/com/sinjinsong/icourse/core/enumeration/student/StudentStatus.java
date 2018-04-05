package com.sinjinsong.icourse.core.enumeration.student;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SinjinSong on 2017/4/28.
 */
public enum StudentStatus {
    UNACTIVATED(0, "未激活"), ACTIVATED(1, "已激活"), FORBIDDEN(2, "已禁用");
    private int code;
    private String desc;

    StudentStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static Map<Integer, StudentStatus> map = new HashMap<>();

    static {
        for (StudentStatus status : values()) {
            map.put(status.code, status);
        }
    }

    public static StudentStatus getByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
