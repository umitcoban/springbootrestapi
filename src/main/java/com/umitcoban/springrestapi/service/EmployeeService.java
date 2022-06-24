package com.umitcoban.springrestapi.service;

import java.beans.JavaBean;
import java.util.List;

import com.umitcoban.springrestapi.model.Employee;

public interface EmployeeService {

	List<Employee> getEmployees();
	
	Employee saveEmployee(Employee employee);
	
	Employee getEmployeeById(long id);
	
	void deleteEmployee(long id);
	
	Employee updateEmployee(Employee employee);
	
	List<Employee> getEmployeesByName(String name);
	
	List<Employee> getEmployeesByNameAndLocation(String name, String location);
}
