package com.updatatech.jasperreportDemo.controler;

import com.updatatech.jasperreportDemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.updatatech.jasperreportDemo.service.EmployeeService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JasperReportController {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("/jasper/report/{id}")
	public String jasperReport(@PathVariable long id) {
		Employee employee = employeeService.finEmployeeById(id);
		return employeeService.generateReport(employee);
	}

}
