package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/13
 **/

@AllArgsConstructor
public enum RightTypeEnum {



    ALL("全部"),

    STUDENT("学生"),

    TEACHER("教师"),

    SCHEDULE("课表"),

    COURSE("课程表"),

    CLASS("班级"),

    CLASSROOM("教室"),

    NOTICE("通知"),

    DEPARTMENT("学院"),

    MONITOR("监控"),

    CONFIG("配置"),

    SYSCONFIG("系统配置");

    private String rightType;


}
