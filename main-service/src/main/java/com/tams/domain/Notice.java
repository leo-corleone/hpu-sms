package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.tams.enums.NoticeVisitEnum;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.util.Date;

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

    @Enumerated(EnumType.STRING)
    private String type;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss" ,timezone = "GMT+8")
    private Date createTime;

    private String remark;

    @Enumerated(EnumType.STRING)
    private String status;

    @TableLogic
    private Integer isDelete;

    @Enumerated(EnumType.STRING)
    private NoticeVisitEnum roleVisible;

}
