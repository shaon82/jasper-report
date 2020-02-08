package com.updatatech.jasperreportDemo.service;

import com.updatatech.jasperreportDemo.model.Employee;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface PdfService {
    void createPdfReport(List<Employee> employees);

    void downloadPdf(String fullPathFile, ServletContext context, HttpServletResponse response, String s);
}
