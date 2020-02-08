package com.updatatech.jasperreportDemo.service;


import com.updatatech.jasperreportDemo.model.Employee;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface EmployeeService {
    List<Employee> findAllEmployee();

    Employee createEmployee(Employee employee);

    boolean createPdf(List<Employee> employees, ServletContext context, HttpServletRequest request, HttpServletResponse response);

	String generateReport(Employee employee);

    Employee finEmployeeById(long id);

//    List<Employee> findEmployeeByCategoryId(Long categoryId);
}
