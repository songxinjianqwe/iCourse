package com.sinjinsong.icourse.core.enumeration.course;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public enum StudyStatus {
    STUDYING(0,"已选课"),QUIT(1,"已退选"),FINISHED(2,"已结课");
    private int code;
    private String desc;

    StudyStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    private static Map<Integer, StudyStatus> map = new HashMap<>();

    static {
        for (StudyStatus status : values()) {
            map.put(status.code, status);
        }
    }

    public static StudyStatus getByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
