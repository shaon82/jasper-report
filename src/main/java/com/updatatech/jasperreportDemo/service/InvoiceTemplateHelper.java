package com.updatatech.jasperreportDemo.service;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.updatatech.jasperreportDemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Service
public class InvoiceTemplateHelper {

    private final TemplateEngine templateEngine;
    private ServletContext context;
    private HttpServletRequest request;

    @Autowired
    public InvoiceTemplateHelper(TemplateEngine templateEngine, ServletContext context, HttpServletRequest request) {
        this.templateEngine = templateEngine;
        this.context = context;
        this.request = request;
    }

    public String getHtmlInvoice(Employee employee){
        WebContext webContext = new WebContext(request,null,context);
        String filepath = "/home/shaon/updateTech/jasperreportDemo/src/main/resources/templates/htmlreport.html";
        context.setAttribute("employee","employee");
        String body = templateEngine.process("filepath", webContext);
        convertToPDF(body);
        return body;
    }

    public void convertToPDF(String body){
        try{
            Document document = new Document(PageSize.A4);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("invoice.pdf"));
            System.out.println("this file is created>>>>>>>>>>>>>");
            document.open();
            InputStream is = new ByteArrayInputStream(body.getBytes());
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
            document.close();
        } catch (IOException e){
            //TODO MESSAGE FOR EXCEPTION

        } catch ( DocumentException e){
            //TODO MESSAGE FOR EXCEPTION

        }
    }

}
