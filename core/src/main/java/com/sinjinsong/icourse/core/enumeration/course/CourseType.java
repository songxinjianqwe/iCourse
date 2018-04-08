package com.sinjinsong.icourse.core.enumeration.course;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public enum CourseType {
    BACKEND(0, "后端开发"), DATA_ANALYZING(1, "数据分析"), FRONTEND(2, "前端开发"), MACHINE_LEARNING(3, "机器学习");
    
    private int code;
    private String desc;

    CourseType(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    
    private static Map<Integer, CourseType> map = new HashMap<>();

    static {
        for (CourseType status : values()) {
            map.put(status.code, status);
        }
    }

    public static CourseType getByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }
    @JsonValue
    public String getDesc() {
        return desc;
    }
}
