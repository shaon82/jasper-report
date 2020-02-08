package com.updatatech.jasperreportDemo.service;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.codec.Base64;
import com.updatatech.jasperreportDemo.model.Employee;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

@Service
public class PdfServiceImpl implements PdfService{
    @Override
    public void createPdfReport(List<Employee> employees) {


        Document document = new Document(PageSize.A4, 15, 15, 45, 30);

        File file = new File("report");
        if (!file.exists()){
            boolean result = file.mkdirs();
            if (result){
                System.out.println("the directory is created.");
            }else {
                System.out.println("the directory is not created.");
            }
        }

        try {
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"employee"+".pdf"));
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
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void downloadPdf(String fullPathFile, ServletContext context, HttpServletResponse response, String filename) {
        File file = new File(fullPathFile);
        response.setContentType("APPLICATION/OCTET-STREAM");
        response.setHeader("Content-Disposition", "attachment; filename=/"
                + filename + "/");

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
