package com.example.chatserver;

import com.example.chatserver.model.employee.Employee;
import com.example.chatserver.model.employee.EmployeeDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private EmployeeDao employeeDao;

	//	@Test
	void contextLoads() {
		Employee employee = new Employee();
		employee.setName("Bruce Wayne");
		employee.setLocation("Building-X");
		employee.setBranch("Security");
		employeeDao.save(employee);
	}

	@Test
	void getAllEmployeesAndDeleteThem() {
		List<Employee> employees = employeeDao.getAllEmployees();
		employees.forEach(employee -> {
			employeeDao.delete(employee);
		});
	}
}
