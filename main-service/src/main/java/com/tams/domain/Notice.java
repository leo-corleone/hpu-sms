package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.tams.enums.NoticeVisitEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@Data
@Accessors(chain = true)
@TableName("notice")
public class Notice implements Serializable{

    @TableId
    private Long nId;

    private String title;

    private String content;

    private String author;

    private LocalDateTime createTime;

    @TableLogic
    private Integer isDelete;

    @Enumerated(EnumType.STRING)
    private NoticeVisitEnum visit;

}
