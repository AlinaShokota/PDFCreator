package com.creator.pdf.controller;

import com.creator.pdf.model.SimpleContract;
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

@CrossOrigin(origins = "https://pdf-creator-front.herokuapp.com/", maxAge = 3600)
@RestController
@RequestMapping("/contract")
public class SimpleContractController {

    @Autowired
    DocumentService<SimpleContract> simpleContractServiceImpl;

    @Autowired
    PdfCreator<SimpleContract> simpleContractPdfCreator;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<List<SimpleContract>> getAllDocuments() {
        List<SimpleContract> documents = simpleContractServiceImpl.getAllDocuments();
        return ResponseEntity.ok(documents);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public ResponseEntity<SimpleContract> get(@PathVariable int id) {
        SimpleContract simpleContract = simpleContractServiceImpl.getDocumentById(id);
        return ResponseEntity.ok(simpleContract);
    }


    //    @RequestMapping(value = "/save", method = RequestMethod.GET)
//    public void save() {
//        SimpleContract simpleContract = new SimpleContract("Aaaaaa", "Cccccc", LocalDate.now());
//        SimpleContract simpleContract2 = new SimpleContract("Dddddd", "Vvvvvv", LocalDate.now());
//        simpleContractServiceImpl.createDocument(simpleContract);
//        simpleContractServiceImpl.createDocument(simpleContract2);
//    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<?> addReport(@RequestBody SimpleContract document) {
        document.setCurrent(LocalDate.now());
        simpleContractServiceImpl.createDocument(document);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/download/{id}", produces = "application/pdf", method = RequestMethod.GET)
    public Resource getFileByReportId(@PathVariable int id, HttpServletResponse response) {
        return simpleContractPdfCreator.getDocumentById(id, response);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteDocument(@PathVariable int id) {
        simpleContractServiceImpl.deleteDocument(id);
    }
}
