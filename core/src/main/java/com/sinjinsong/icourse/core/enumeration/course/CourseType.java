package com.sinjinsong.icourse.core.enumeration.course;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public enum CourseType {
    C_PLUS_PLUS(0, "C++"), JAVA(1, "Java"), FrontEnd(2, "前端"), MachineLearning(3, "机器学习");
    
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
}
