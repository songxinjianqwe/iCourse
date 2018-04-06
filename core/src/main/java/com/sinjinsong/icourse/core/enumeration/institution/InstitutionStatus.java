package com.sinjinsong.icourse.core.enumeration.institution;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sinjinsong
 * @date 2018/4/5
 */
public enum InstitutionStatus {

    NOT_APPROVED(0, "未批准"), APPROVED(1, "已批准");
    private int code;
    private String desc;

    InstitutionStatus(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }
    

    private static Map<Integer, InstitutionStatus> map = new HashMap<>();

    static {
        for (InstitutionStatus status : values()) {
            map.put(status.code, status);
        }
    }

    public static InstitutionStatus getByCode(int code) {
        return map.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
