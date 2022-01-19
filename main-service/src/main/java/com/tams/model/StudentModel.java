package com.tams.model;

import com.tams.domain.Student;
import lombok.Data;

/**
 * @author swiChen
 * @date 2022/1/17
 **/

@Data
public class StudentModel extends Student {

    private String imagePath;

    private String teacher;

    private String clazz;

    private String department;

    /**
     * 是否在线
     */
    private String state;

    private String grade;

}
