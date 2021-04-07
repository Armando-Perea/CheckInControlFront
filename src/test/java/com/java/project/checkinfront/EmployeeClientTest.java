package com.java.project.checkinfront;


import java.util.Arrays;
import java.util.List;

import com.java.project.checkin.client.EmployeeClient;
import com.java.project.checkin.models.Employee;

public class EmployeeClientTest {

	public void createDummyEmployee() {
		Employee employee = new Employee(); 
		employee.setIdEmployee(2);
		employee.setEmployeeName("TestName");
		employee.setAddress("Test Address");
		employee.setCellphone("5526987454512457");
		employee.setRole("Recepcionista");
		employee.setPassword("123456");
		employee = EmployeeClient.addEmployee(employee);
		System.out.println("EMPLOYEE CREATED: "+employee.toString());
	}
	
	public void updateDummyEmployee() {
		Employee employee = new Employee(); 
		employee.setIdEmployee(2);
		employee.setEmployeeName("TestName Updated");
		employee.setAddress("Test Address Updated");
		employee.setCellphone("552698 Updated");
		employee.setRole("Recepcionista Updated");
		int response = EmployeeClient.updateEmployee(employee);
		System.out.println("UPDATE RESPONSE: "+response);
	}
	
	public void getAllEmployee() {
		List<Employee> admin = Arrays.asList(EmployeeClient.getAllEmployees());
		admin.forEach(System.out::println);
	}
	
	public void getEmployeeById(Integer id) {
		Employee employee = EmployeeClient.getEmployeeById(id);
		System.out.println("EMPLOYEE ID: "+employee.toString());
	}
	
	public void getEmployeeByName(String name) {
		List<Employee> admin = Arrays.asList(EmployeeClient.getEmployeeByName(name));
		admin.forEach(System.out::println);
	}
	
	public void deleteEmployee(Integer id) {
		Integer admin = EmployeeClient.deleteEmployee(id);
		System.out.println("EMPLOYEE REMOVED: "+admin);
	}

	public static void main(String[] args) {
		EmployeeClientTest employeeClientTest = new EmployeeClientTest();
		employeeClientTest.createDummyEmployee();
		//employeeClientTest.updateDummyEmployee();
		//employeeClientTest.getEmployeeById(2);
		//employeeClientTest.getEmployeeByName("TEST");
		//employeeClientTest.getAllEmployee();
		//employeeClientTest.deleteEmployee(2);
		// EVERYTHING WORKING OK !!!
	}
	
}