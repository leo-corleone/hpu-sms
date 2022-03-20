package com.tams.model;

import com.tams.domain.Clazz;
import lombok.Data;

/**
 * @author swiChen
 * @date 2022/3/13
 **/

@Data
public class ClassModel extends Clazz {

    private String teacher;

    private String department;

}
