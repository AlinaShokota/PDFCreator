package com.creator.pdf.service;


import com.creator.pdf.exception.PdfCreateException;
import com.creator.pdf.model.WebsiteQuote;
import com.itextpdf.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class WebsiteQuoteCreatorServiceImpl implements WebsiteQuoteCreatorService {
    private static final String PDF_CREATING_EXCEPTION_MESSAGE = "Cannot create PDF file!";

    @Autowired
    WebsiteQuoteService websiteQuoteService;

    @Override
    public Resource getDocumentById(Integer id, HttpServletResponse response) {
        WebsiteQuote websiteQuoteDocument = websiteQuoteService.getDocument(id);
        createPdf(websiteQuoteDocument);
        String fileName = "src/main/resources/website.pdf";
        response.setContentType("application/pdf");
        return new FileSystemResource(fileName);
    }

    @Override
    public void createPdf(WebsiteQuote document) {
        try {
            OutputStream outputStream = new FileOutputStream("src/main/resources/website.pdf");
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(setReportInfoVariables(document));
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (IOException | DocumentException e) {
            System.out.println("ERROR: " + PDF_CREATING_EXCEPTION_MESSAGE);
            throw new PdfCreateException(PDF_CREATING_EXCEPTION_MESSAGE);
        }
    }

    private String setReportInfoVariables(WebsiteQuote document) {
        Context context = new Context();
        context.setVariable("companyName", document.getCompanyName());
        context.setVariable("dateOfCreation", document.getDateOfCreation());
        context.setVariable("customerName", document.getCustomerName());
        context.setVariable("dateValid", document.getDateValid());
        context.setVariable("pricingRows", document.getPricingRows());
        return configureTemplateEngine().process("websiteTemplate", context);
    }

    private TemplateEngine configureTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }


}
