package com.umitcoban.springrestapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.umitcoban.springrestapi.model.Employee;
import com.umitcoban.springrestapi.repository.EmployeeRepository;


@Service
public class EmployeeServiceImp implements EmployeeService {

	private static List<Employee> employees = new ArrayList<>();
	
	@Autowired
	private EmployeeRepository eRepository;
	
	@Override
	public List<Employee> getEmployees() {
		
		return eRepository.findAll();
	}


	@Override
	public Employee saveEmployee(Employee employee) {
		return eRepository.save(employee);
	}


	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = eRepository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		}
		else
			throw new RuntimeException("Employe is Not Found By ID : " + id);
	}


	@Override
	public void deleteEmployee(long id) {
		eRepository.deleteById(id);
		
	}


	@Override
	public Employee updateEmployee(Employee employee) {
		
		return eRepository.save(employee);
	}


	@Override
	public List<Employee> getEmployeesByName(String name) {
		return eRepository.findByName(name);
	}


	@Override
	public List<Employee> getEmployeesByNameAndLocation(String name, String location) {
		return eRepository.findByNameAndLocation(name, location);
	}

}
