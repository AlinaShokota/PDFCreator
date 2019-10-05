package com.creator.pdf.controller;

import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.pdf.PdfCreator;
import com.creator.pdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/website")
public class WebsitePriceDocumentController {

    @Autowired
    DocumentService<WebsitePriceDocument> websitePriceDocumentServiceImpl;

    @Autowired
    PdfCreator<WebsitePriceDocument> websitePriceDocumentPdfCreator;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<WebsitePriceDocument>> getAllDocuments() {
        List<WebsitePriceDocument> documents = websitePriceDocumentServiceImpl.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<WebsitePriceDocument> get(@PathVariable int id) {
        WebsitePriceDocument websitePriceDocument = websitePriceDocumentServiceImpl.getDocumentById(id);
        System.out.println(websitePriceDocument);
        return ResponseEntity.ok(websitePriceDocument);
    }


    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> addReport(@RequestBody WebsitePriceDocument document) {

        document.setCurrent(LocalDate.now());
        websitePriceDocumentServiceImpl.createDocument(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/download/{id}", produces = "application/pdf", method = RequestMethod.GET)
    public Resource getFileByReportId(@PathVariable int id, HttpServletResponse response) {
        return websitePriceDocumentPdfCreator.getDocumentById(id, response);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable int id) {
        websitePriceDocumentServiceImpl.deleteDocument(id);
    }
}
