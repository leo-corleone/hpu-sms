package com.tams.model;

import com.tams.domain.Clazz;
import com.tams.domain.Department;
import com.tams.domain.Teacher;
import lombok.Data;

import java.util.List;

/**
 * @author swiChen
 * @date 2022/3/15
 **/

@Data
public class DepartmentModel extends Department {

   private String presidents;

   private String vicePresidents;

   private List<Clazz> clazzes;

   private List<Teacher> teachers;

}
