package org.cp.springmvc.crud.converters;

import org.cp.springmvc.crud.entities.Department;
import org.cp.springmvc.crud.entities.Employee;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter implements Converter<String, Employee> {

	@Override
	public Employee convert(String source) {
		if(null != source) {
			System.out.println("source: " + source);
			String [] args = source.split("-");
			if(null!=args&&args.length==4) {
				String lastName = args[0];
				String email = args[1];
				String gender = args[2];
				String departmentId = args[3];
				Department department = new Department(Integer.parseInt(departmentId), null);
				Employee employee = new Employee(null, lastName, email, Integer.parseInt(gender), department);
				System.out.println("convert: " + employee);
				return employee;
			}
		}
		
		return null;
	}

}
