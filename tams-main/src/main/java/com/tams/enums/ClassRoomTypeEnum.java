package com.tams.enums;

import lombok.AllArgsConstructor;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@AllArgsConstructor
public enum ClassRoomTypeEnum {

    TEACHING("教室"),

    OFFICE("办公室"),

    COMPUTER("机房"),

    OTHER("其它");

    private String desc;

    public String getDesc() {
        return desc;
    }
}
