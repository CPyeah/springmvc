package org.cp.springmvc.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.cp.springmvc.crud.entities.Department;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDAO {
	
	public static List<Department> departmentList = new ArrayList();
	
	static {
		departmentList.add(new Department(101, "外交部"));
		departmentList.add(new Department(102, "宣传部"));
		departmentList.add(new Department(103, "农业部"));
		departmentList.add(new Department(104, "科技部"));
		departmentList.add(new Department(105, "中常委"));
	}
	
	public List getAllDepartment() {
		return departmentList;
	}
	
	public Department getDepartmentById(Integer id) {
		for (int i = 0; i < departmentList.size(); i++) {
			Department item = departmentList.get(i);
			Integer did = item.getId();
			if (did == id) {
				return item;
			}
		}
		return null;
	}

}
