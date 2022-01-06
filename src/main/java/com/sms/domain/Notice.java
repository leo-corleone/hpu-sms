/**
 * 
 */
package com.sms.domain;

import java.io.Serializable;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author swiChen
 * @date 2022/1/5
 **/

@Data
@Accessors(chain = true)
@TableName("notice")
public class Notice implements Serializable{

}
