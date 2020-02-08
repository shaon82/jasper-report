package com.updatatech.jasperreportDemo.controler;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.updatatech.jasperreportDemo.model.Employee;
import com.updatatech.jasperreportDemo.service.EmployeeService;
import com.updatatech.jasperreportDemo.service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Controller
public class PdfController {

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private ServletContext context;

    @Autowired
    private PdfService pdfService;

    @GetMapping("/create-pdf")
    public void createPdfPage(HttpServletRequest request, HttpServletResponse response){

        List<Employee>employees = employeeService.findAllEmployee();
        pdfService.createPdfReport(employees);
        String fullPathFile = context.getRealPath("report/employee.pdf");
//        pdfService.downloadPdf(fullPathFile,context, response, "employee.pdf");

    }

    @GetMapping("/download-pdf")
    public void downloadPdf(HttpServletResponse response)throws FileNotFoundException {
        File file = new File("reports");
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=/"
                +"test.pdf"+ "/");

        try {
            FileInputStream fileInputStream = new FileInputStream(file+"/"+"test.pdf");
            int i;
            while ((i = fileInputStream.read()) != -1){
                response.getWriter().write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
