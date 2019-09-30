package com.creator.pdf.pdf;

import com.creator.pdf.exception.PdfCreationException;
import com.creator.pdf.model.Document;
import com.itextpdf.text.DocumentException;
import org.springframework.core.io.Resource;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public abstract class PdfCreator<T extends Document> {
    private static final String PDF_CREATING_EXCEPTION_MESSAGE = "Cannot create PDF file!";

    public abstract Resource getDocumentById(Integer id, HttpServletResponse response);

    public TemplateEngine configureTemplateEngine() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML");
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
    public void createPdf(T document, String filename) {
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(setReportInfoVariables(document));
            renderer.layout();
            renderer.createPDF(outputStream);
            outputStream.close();
        } catch (IOException | DocumentException e) {
            System.out.println("ERROR: " + PDF_CREATING_EXCEPTION_MESSAGE);
            e.printStackTrace();
            throw new PdfCreationException(PDF_CREATING_EXCEPTION_MESSAGE);

        }
    }
    public abstract String setReportInfoVariables(T document);
}
