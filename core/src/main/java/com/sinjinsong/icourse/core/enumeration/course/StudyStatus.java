package com.sinjinsong.icourse.core.enumeration.course;

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
}
