package com.updatatech.jasperreportDemo.controler;


import com.updatatech.jasperreportDemo.model.Category;
import com.updatatech.jasperreportDemo.model.Employee;
import com.updatatech.jasperreportDemo.service.CategoryService;
import com.updatatech.jasperreportDemo.service.EmployeeService;
import com.updatatech.jasperreportDemo.service.InvoiceTemplateHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private InvoiceTemplateHelper invoiceTemplateHelper;


    @GetMapping(value = "/employee-list", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<Employee> getsAllEmployee(){
        return employeeService.findAllEmployee();
    }

    @PostMapping("/save-category")
    public Category createCategory(@RequestBody Category category){
        return categoryService.save(category);
    }

    @PostMapping("/create-employee")
    public Employee createEmployee(@RequestBody Employee employee){


        return employeeService.createEmployee(employee);
    }




//    @GetMapping("/employee/{categoryId}")
//    public  List<Employee> getEmployeeByCategoryId(@PathVariable("categoryId") Long categoryId){
//        return employeeService.findEmployeeByCategoryId(categoryId);
//    }
}
