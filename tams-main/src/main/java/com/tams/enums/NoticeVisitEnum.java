package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@AllArgsConstructor
public enum NoticeVisitEnum {

    ALL("全部"),

    STUDENT("学生"),

    TEACHER("教师");

    private String visit;

    public String getVisit() {
        return visit;
    }

}
