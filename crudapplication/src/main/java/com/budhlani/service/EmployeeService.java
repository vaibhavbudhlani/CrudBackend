package com.budhlani.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.budhlani.model.Employee;
import com.budhlani.repository.EmployeeRepository;

@Service
public class EmployeeService {
	@Autowired
    public EmployeeRepository employeeRepository;
	
	public List<Employee> getAllEmployees() {
		
		return employeeRepository.findAll();
	}

	public Employee addEmployee(Employee employee) {
	
		return employeeRepository.save(employee);
	}

	public Optional<Employee> getEmployee(long id) {
		
		return employeeRepository.findById(id);
	}

	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		employeeRepository.delete(employee);
		
	}

}
