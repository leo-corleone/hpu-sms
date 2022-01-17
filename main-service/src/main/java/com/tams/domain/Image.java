package com.tams.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * @author swiChen
 * @date 2022/1/14
 **/

@Data
@Accessors(chain = true)
@TableName("image")
public class Image {

    @TableId
    private Long iId;

    private String name;

    private String path;

    private String type;

    private Integer size;

    private String bucketName;

    private LocalDateTime uploadTime;

    @TableLogic
    private Integer isDelete;



}
