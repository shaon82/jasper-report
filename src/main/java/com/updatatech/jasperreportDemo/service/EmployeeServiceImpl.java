package com.updatatech.jasperreportDemo.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.updatatech.jasperreportDemo.model.Employee;
import com.updatatech.jasperreportDemo.repository.EmployeeRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAllEmployee() {
        return (List<Employee>) employeeRepository.findAll();
    }

    @Override
    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public boolean createPdf(List<Employee> employees, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        Document document = new Document(PageSize.A4, 15, 15, 45,30);
        try {
            String filePath = context.getRealPath("reports");
            File file = new File(filePath);
            boolean exist = new File(filePath).exists();
            if (!exist){
                new File(filePath).mkdirs();
            }

            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"employees"+".pdf"));
            document.open();

            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Paragraph paragraph = new Paragraph("All employees", mainFont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial", 9, BaseColor.BLACK);

            float[] columnWidths = {2f, 2f, 2f, 2f};
            table.setWidths(columnWidths);

            PdfPCell firstName = new  PdfPCell(new Paragraph("First Name",tableHeader));
            firstName.setBorderColor(BaseColor.BLACK);
            firstName.setPaddingLeft(10);
            firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
            firstName.setVerticalAlignment(Element.ALIGN_CENTER);
            firstName.setBackgroundColor(BaseColor.GRAY);
            firstName.setExtraParagraphSpace(5f);
            table.addCell(firstName);


            PdfPCell lastName = new  PdfPCell(new Paragraph("Last Name",tableHeader));
            lastName.setBorderColor(BaseColor.BLACK);
            lastName.setPaddingLeft(10);
            lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
            lastName.setVerticalAlignment(Element.ALIGN_CENTER);
            lastName.setBackgroundColor(BaseColor.GRAY);
            lastName.setExtraParagraphSpace(5f);
            table.addCell(lastName);


            PdfPCell email = new  PdfPCell(new Paragraph("Email",tableHeader));
            email.setBorderColor(BaseColor.BLACK);
            email.setPaddingLeft(10);
            email.setHorizontalAlignment(Element.ALIGN_CENTER);
            email.setVerticalAlignment(Element.ALIGN_CENTER);
            email.setBackgroundColor(BaseColor.GRAY);
            email.setExtraParagraphSpace(5f);
            table.addCell(email);

            PdfPCell phone = new  PdfPCell(new Paragraph("Phone",tableHeader));
            phone.setBorderColor(BaseColor.BLACK);
            phone.setPaddingLeft(10);
            phone.setHorizontalAlignment(Element.ALIGN_CENTER);
            phone.setVerticalAlignment(Element.ALIGN_CENTER);
            phone.setBackgroundColor(BaseColor.GRAY);
            phone.setExtraParagraphSpace(5f);
            table.addCell(phone);


            for (Employee employee: employees){

                PdfPCell firstNameValue = new  PdfPCell(new Paragraph(employee.getFirstName(),tableBody));
                firstNameValue.setBorderColor(BaseColor.BLACK);
                firstNameValue.setPaddingLeft(10);
                firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
                firstNameValue.setBackgroundColor(BaseColor.WHITE);
                firstNameValue.setExtraParagraphSpace(5f);
                table.addCell(firstNameValue);

                PdfPCell lastNameValue = new  PdfPCell(new Paragraph(employee.getLastName(),tableBody));
                lastNameValue.setBorderColor(BaseColor.BLACK);
                lastNameValue.setPaddingLeft(10);
                lastNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                lastNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
                lastNameValue.setBackgroundColor(BaseColor.WHITE);
                lastNameValue.setExtraParagraphSpace(5f);
                table.addCell(lastNameValue);

                PdfPCell emailValue = new  PdfPCell(new Paragraph(employee.getEmail(),tableBody));
                emailValue.setBorderColor(BaseColor.BLACK);
                emailValue.setPaddingLeft(10);
                emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
                emailValue.setBackgroundColor(BaseColor.WHITE);
                emailValue.setExtraParagraphSpace(5f);
                table.addCell(emailValue);

                PdfPCell phoneValue = new  PdfPCell(new Paragraph(employee.getPhone(),tableBody));
                phoneValue.setBorderColor(BaseColor.BLACK);
                phoneValue.setPaddingLeft(10);
                phoneValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                phoneValue.setVerticalAlignment(Element.ALIGN_CENTER);
                phoneValue.setBackgroundColor(BaseColor.WHITE);
                phoneValue.setExtraParagraphSpace(5f);
                table.addCell(phoneValue);
            }

            document.add(table);
            document.close();
            pdfWriter.close();
            return true;

        }catch (Exception e){
            return false;
        }
    }

	@Override
	public String generateReport(Employee employee) {
         List<Employee>employees = employeeRepository.findAll();
         for(Employee employee1: employees) {
        	 System.out.println(employee1.getFirstName());
         }
		try {

			String reportPath = "/home/shaon/updateTech/jasperreportDemo/reports";

            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath+"/"+"mp.jrxml");
            

//            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(employees);
            
            
            JRDataSource source = new JREmptyDataSource();

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("employees", employees);
            
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,source);

            JasperExportManager.exportReportToPdfFile(jasperPrint,reportPath+"/hotel8.pdf");
            System.out.println("done this report");
            return "the file is created.";
			
		}catch (Exception e) {
			e.printStackTrace();
			return "the file is not created";
		}
	}

    @Override
    public Employee finEmployeeById(long id) {
        return employeeRepository.findEmployeeById(id);
    }

//    @Override
//    public List<Employee> findEmployeeByCategoryId(Long categoryId) {
//        Category category = categoryService.findCategoryById(categoryId);
//        System.out.println("category id: "+category.getId());
//        return employeeRepository.findByCategoryId(category.getId());
//    }



}
