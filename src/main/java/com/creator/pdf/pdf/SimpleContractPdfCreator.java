package com.creator.pdf.pdf;

import com.creator.pdf.model.SimpleContract;
import com.creator.pdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import javax.servlet.http.HttpServletResponse;

@Component
public class SimpleContractPdfCreator extends PdfCreator<SimpleContract> {

    private final String fileName = "src/main/resources/simpleContract.pdf";

    @Autowired
    DocumentService<SimpleContract> simpleContractServiceImpl;

    @Override
    public Resource getDocumentById(Integer id, HttpServletResponse response) {
        SimpleContract simpleContract = simpleContractServiceImpl.getDocumentById(id);
        createPdf(simpleContract, fileName);
        response.setContentType("application/pdf");
        return new FileSystemResource(fileName);
    }

    @Override
    public String setReportInfoVariables(SimpleContract document) {
        Context context = new Context();
        context.setVariable("current", document.getCurrent());
        context.setVariable("companyName", document.getCompanyName());
        context.setVariable("customerCompanyName", document.getCustomerCompanyName());
        context.setVariable("expirationDate", document.getExpirationDate());

        return configureTemplateEngine().process("simpleContractTemplate", context);

    }
}
