package com.tams.model;

import com.tams.domain.Teacher;
import lombok.Data;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/2/19
 **/

@Data
public class TeacherModel extends Teacher {

  private String department;

  private Boolean isPresident = false;

  private Boolean isVicePresident = false;

  private List<String> clazz;

  private Boolean isT;

}
