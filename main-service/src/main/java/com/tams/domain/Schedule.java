package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tams.enums.ScheduleWeekEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Data
@Accessors(chain = true)
@TableName("schedule")
public class Schedule implements Serializable {

    @TableId
    private Long scId;

    private Long cNo;

    private Long tId;

    private Long cId;

    private Long roomId;

    @Enumerated(EnumType.STRING)
    private ScheduleWeekEnum week;

    private Integer order;

    private Date start;

    private Integer period ;

    private LocalDateTime createTime;

    private Long createBy;

    @TableLogic
    private Integer isDelete;
}
