package com.tams.model;

import com.tams.domain.Department;
import lombok.Data;

/**
 * @author swiChen
 * @date 2022/3/15
 **/

@Data
public class DepartmentModel extends Department {

   private String presidents;

   private String vicePresidents;

}
