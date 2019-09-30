package com.creator.pdf.controller;

import com.creator.pdf.model.PriceRow;
import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.pdf.PdfCreator;
import com.creator.pdf.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
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
        System.out.println(documents);
        return ResponseEntity.ok(documents);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<WebsitePriceDocument> get(@PathVariable int id) {
        WebsitePriceDocument websitePriceDocument = websitePriceDocumentServiceImpl.getDocumentById(id);
        System.out.println(websitePriceDocument);
        return ResponseEntity.ok(websitePriceDocument);
    }


    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public void save() {
        PriceRow priceRow = new PriceRow("A", "u", 23);
        PriceRow priceRow2 = new PriceRow("A", "a", 22);
        PriceRow priceRow3 = new PriceRow("F", "a", 23);
        PriceRow priceRow4 = new PriceRow("A", "t", 28);
        PriceRow priceRow5 = new PriceRow("A", "a", 23);
        PriceRow priceRow6 = new PriceRow("F", "a", 63);
        PriceRow priceRow7 = new PriceRow("A", "w", 23);
        PriceRow priceRow8 = new PriceRow("A", "a", 33);
        PriceRow priceRow9 = new PriceRow("F", "a", 13);
        List<PriceRow> priceRows = new ArrayList<>();
        priceRows.add(priceRow);
        priceRows.add(priceRow2);
        priceRows.add(priceRow3);
        priceRows.add(priceRow4);
        priceRows.add(priceRow5);
        priceRows.add(priceRow6);
        priceRows.add(priceRow7);
        priceRows.add(priceRow8);
        priceRows.add(priceRow9);
        WebsitePriceDocument websitePriceDocument = new WebsitePriceDocument("AAAA", "AAAA", priceRows);
        websitePriceDocumentServiceImpl.createDocument(websitePriceDocument);

    }

    @RequestMapping(value = "/download/{id}", produces = "application/pdf", method = RequestMethod.GET)
    public Resource getFileByReportId(@PathVariable int id, HttpServletResponse response) {
        return websitePriceDocumentPdfCreator.getDocumentById(id, response);
    }
}