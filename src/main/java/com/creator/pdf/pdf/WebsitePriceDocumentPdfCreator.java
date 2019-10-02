package com.creator.pdf.pdf;

import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;

@Component
public class WebsitePriceDocumentPdfCreator extends PdfCreator<WebsitePriceDocument> {

    private final String fileName = "src/main/resources/website.pdf";

    @Autowired
    DocumentService<WebsitePriceDocument> websitePriceDocumentServiceImpl;
    @Override
    public Resource getDocumentById(Integer id, HttpServletResponse response) {
        WebsitePriceDocument websitePriceDocument = websitePriceDocumentServiceImpl.getDocumentById(id);
        createPdf(websitePriceDocument, fileName);
        response.setContentType("application/pdf");
        return new FileSystemResource(fileName);
    }

    @Override
    public String setReportInfoVariables(WebsitePriceDocument document) {
        Context context = new Context();
        context.setVariable("current", document.getCurrent());
        context.setVariable("companyName", document.getCompanyName());
        context.setVariable("customerCompanyName", document.getCustomerCompanyName());
        context.setVariable("pricingRows", document.getPricingRows());
        context.setVariable("cost", document.getCost());

        return configureTemplateEngine().process("websiteTemplate", context);

    }
}
