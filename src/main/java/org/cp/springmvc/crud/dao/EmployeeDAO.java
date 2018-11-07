package org.cp.springmvc.crud.dao;

import java.util.ArrayList;
import java.util.List;

import org.cp.springmvc.crud.entities.Department;
import org.cp.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDAO {
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	public static List<Employee> employeeList = new ArrayList();;
	
	public static Integer initId = 1007;
	
	static {
		employeeList.add(new Employee(1001, "E-AA", "aa@cp.com", 1, new Department(101, "外交部")));
		employeeList.add(new Employee(1002, "E-BB", "bb@cp.com", 1, new Department(103, "农业部")));
		employeeList.add(new Employee(1003, "E-CC", "cc@cp.com", 0, new Department(104, "科技部")));
		employeeList.add(new Employee(1004, "E-DD", "dd@cp.com", 1, new Department(101, "外交部")));
		employeeList.add(new Employee(1005, "E-EE", "ee@cp.com", 0, new Department(102, "宣传部")));
		employeeList.add(new Employee(1006, "E-FF", "ff@cp.com", 1, new Department(104, "科技部")));
		employeeList.add(new Employee(1007, "E-GG", "gg@cp.com", 0, new Department(105, "中常委")));
	}
	
	public void save(Employee employee) {
		if(null == employee.getId()) {
			initId ++;
			employee.setId(initId);
		}
		if (null == employee.getDepartment().getDepartmentName()) {
			Integer departmentId = employee.getDepartment().getId();
			employee.setDepartment(departmentDAO.getDepartmentById(departmentId));
		}
		employeeList.add(employee);
	}
	
	public void delete(Integer id) {
		for (int i = 0; i < employeeList.size(); i++) {
			Employee item = employeeList.get(i);
			if(item.getId().equals(id)) {
				employeeList.remove(i);
				return;
			}
		}
	}
	
	public List<Employee> getAllEmployee() {
		return employeeList;
	}
	
	public Employee getEmployeeById(Integer id) {
		for (int i = 0; i < employeeList.size(); i++) {
			Employee item = employeeList.get(i);
			if(item.getId().equals(id)) {
				return item;
			}
		}
		return null;
	}

}
