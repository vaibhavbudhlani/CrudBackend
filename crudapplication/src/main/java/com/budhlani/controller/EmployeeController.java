package com.budhlani.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.budhlani.exceptions.ResourceNotFoundException;
import com.budhlani.model.Employee;
import com.budhlani.service.EmployeeService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v1/")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;
	
	@RequestMapping("/employees")
	public List<Employee> getAllemployees(){
		return employeeService.getAllEmployees();
	}
	@PostMapping("/addemployee")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
		System.out.print("Inside Add Employee.."+employee);
		Employee emp = employeeService.addEmployee(employee);
		return  new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	@GetMapping("/getemployee/{id}")
	public ResponseEntity<Employee> getEmployee(@PathVariable long id){
		
		Employee emp = employeeService.getEmployee(id).orElseThrow(()->
			new ResourceNotFoundException("Employee Not Found")
		);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	@PutMapping("/updateemployee/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee employee){
		
		Employee emp = employeeService.getEmployee(id).orElseThrow(()->
			new ResourceNotFoundException("Employee Not Found")
		);
		emp.setFirstname(employee.getFirstname());
		emp.setLastname(employee.getLastname());
		emp.setEmailId(employee.getEmailId());
		employeeService.addEmployee(emp);
		return new ResponseEntity<Employee>(emp,HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteemployee/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployee(@PathVariable long id){
		Employee emp = employeeService.getEmployee(id).orElseThrow(()->
		new ResourceNotFoundException("Employee Not Found")
	);
		employeeService.deleteEmployee(emp);
		Map<String,Boolean> hm = new HashMap<>();
		hm.put("Deleted", Boolean.TRUE);
		return new ResponseEntity<Map<String,Boolean>>(hm,HttpStatus.OK);
	}

}
