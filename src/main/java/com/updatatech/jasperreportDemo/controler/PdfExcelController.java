package com.updatatech.jasperreportDemo.controler;


import com.updatatech.jasperreportDemo.model.Employee;
import com.updatatech.jasperreportDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
public class PdfExcelController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ServletContext context;


    @GetMapping("/all-employee")
    private String getAllEmployee(Model model){
        List<Employee>employees = employeeService.findAllEmployee();
        model.addAttribute("employees",employees);
        return "all-employee";
    }


    @GetMapping("/add/employee")
    public String addEmployee(Model model){
        model.addAttribute("employee", new Employee());
        return "add-employee";
    }

    @PostMapping("/add/employee")
    public String saveEmployee(Model model, @Valid Employee employee, BindingResult result){
        if (result.hasErrors()){
            model.addAttribute("error", true);
            return "add-employee";
        }
        employeeService.createEmployee(employee);
        return "redirect:/all-employee";
    }


    @GetMapping("/createPdf")
    public void createPdf(HttpServletRequest request, HttpServletResponse response){
        List<Employee>employees = employeeService.findAllEmployee();
        boolean isFlag = employeeService.createPdf(employees,context,request,response);
        if (isFlag){
            String fullPath = request.getServletContext().getRealPath("reports/"+"employees"+".pdf");
            fileDownload(fullPath,response,"employees.pdf");
        }
    }

    private void fileDownload(String fullPath, HttpServletResponse response, String fileName) {
        File file = new File(fullPath);
        final int BUFFER_SIZE = 4096;
        if (file.exists()){
            try {
                FileInputStream inputStream = new FileInputStream(file);
                String mineTye = context.getMimeType(fullPath);
                response.setContentType(mineTye);
                response.setHeader("content-disposition", "attachment; filename="+fileName);
                OutputStream outputStream = response.getOutputStream();
                byte[] buffer = new byte[BUFFER_SIZE];
                int byteRead = -1;
                while ((byteRead=inputStream.read(buffer))!=-1){
                    outputStream.write(buffer,0,byteRead);
                }
                inputStream.close();
                outputStream.close();
                file.delete();
            }catch (Exception e){
                    e.printStackTrace();
            }
        }
    }
}
