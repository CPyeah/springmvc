package org.cp.springmvc.crud.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cp.springmvc.crud.dao.DepartmentDAO;
import org.cp.springmvc.crud.dao.EmployeeDAO;
import org.cp.springmvc.crud.entities.Department;
import org.cp.springmvc.crud.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("employee")
@Controller
public class EmployeeHandler {
	
	@Autowired
	private EmployeeDAO employeeDAO;
	
	@Autowired
	private DepartmentDAO departmentDAO;
	
	@ResponseBody
	@RequestMapping("/getAllEmployee")
	public Map<String, Object> getAllEmployee() {
		List<Employee> employeeList = employeeDAO.getAllEmployee();
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("employeeList", employeeList);
		return modelMap;
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(@PathVariable("id") Integer id) {
		employeeDAO.delete(id);
		return "redirect:/curdList";
	}
	
	@ResponseBody
	@RequestMapping("/getAllDepartment")
	public Map<String, Object> getAllDepartment() {
		List<Department> list = departmentDAO.getAllDepartment();
		Map<String, Object> modelMap = new HashMap<>();
		modelMap.put("departmentList", list);
		return modelMap;
	}
	
	@RequestMapping("/add")
	public String add(Employee employee) {
		System.out.println(employee);
		employeeDAO.save(employee);
		return "redirect:/curdList";
	}
	
	@RequestMapping("/conversionEmployee")
	public String conversionEmployee(@RequestParam("employee") Employee employee) {
		System.out.println("conversionEmployee:  " + employee);
		employeeDAO.save(employee);
		return "redirect:/curdList";
	}
	
//	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setDisallowedFields("email");
	}

}
