package com.emp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.emp.exception.ResourceNotFoundException;
import com.emp.model.Employee;
import com.emp.repository.EmployeeRepository;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController 
{
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//get all employees
	
	@GetMapping("/employees")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}
	
	//create employee rest api
	@PostMapping("/employees")
	@CrossOrigin(origins = "http://localhost:4200")
	public Employee createEmployee(@RequestBody Employee employee)
	{
		return employeeRepository.save(employee);
	}
	
	//get employee by id rest api
	@GetMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" +id));
		return ResponseEntity.ok(employee);
	}
	//update employee rest api
	@PutMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Employee> updateemployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" +id));
		employee.setFirstname(employeeDetails.getFirstname());
		employee.setFirstname(employeeDetails.getLastname());
		employee.setFirstname(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		return ResponseEntity.ok(updatedEmployee);
	}
	
	//delete employee rest api
	@DeleteMapping("/employees/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) {
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id:" +id));
		employeeRepository.delete(employee);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
}
