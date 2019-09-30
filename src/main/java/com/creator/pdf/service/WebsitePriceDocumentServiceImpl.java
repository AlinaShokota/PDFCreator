package com.creator.pdf.service;

import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.repository.WebsitePriceDocumentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsitePriceDocumentServiceImpl implements DocumentService<WebsitePriceDocument> {

    private WebsitePriceDocumentRepo websitePriceDocumentRepo;

    public WebsitePriceDocumentServiceImpl(WebsitePriceDocumentRepo websitePriceDocumentRepo) {
        this.websitePriceDocumentRepo = websitePriceDocumentRepo;
    }

    @Override
    public WebsitePriceDocument getDocumentById(int id) {
        return websitePriceDocumentRepo.findById(id).get();
    }

    @Override
    public List<WebsitePriceDocument> getAllDocuments() {
        return websitePriceDocumentRepo.findAll();
    }

    @Override
    public void createDocument(WebsitePriceDocument doc) {
        websitePriceDocumentRepo.save(doc);
    }
}
