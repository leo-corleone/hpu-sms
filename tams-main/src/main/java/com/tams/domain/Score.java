package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author swiChen
 * @date 2022/1/12
 **/

@Data
@Accessors(chain = true)
@TableName("score")
public class Score implements Serializable {

     @TableId
    private Long sNo;

    private Long sId;

    private Long cId;

    private Long tId;

    private Integer grade;

    private LocalDateTime enterTime;

}
