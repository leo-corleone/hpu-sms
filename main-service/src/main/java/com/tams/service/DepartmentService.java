package com.tams.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.tams.domain.Department;
import com.tams.dto.PageParam;
import com.tams.dto.PageResult;
import com.tams.model.DepartmentModel;
import com.tams.model.OptionsModel;

import java.util.Set;

public interface DepartmentService extends IService<Department> {

     PageResult getList(PageParam pageParam);

     boolean updateDepartment(DepartmentModel departmentModel);

     boolean addDepartment(DepartmentModel departmentModel);

     Set<OptionsModel> departmentTeacher();
	
}
