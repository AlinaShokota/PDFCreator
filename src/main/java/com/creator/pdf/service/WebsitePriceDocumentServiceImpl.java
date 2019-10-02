package com.creator.pdf.service;

import com.creator.pdf.exception.NoSuchDucumentException;
import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.repository.WebsitePriceDocumentRepo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class WebsitePriceDocumentServiceImpl implements DocumentService<WebsitePriceDocument> {
    private final ResourceBundle strings = ResourceBundle.getBundle("strings_en", Locale.ENGLISH);
    private static final Logger LOGGER = LogManager.getLogger(WebsitePriceDocumentServiceImpl.class.getName());

    private WebsitePriceDocumentRepo websitePriceDocumentRepo;

    public WebsitePriceDocumentServiceImpl(WebsitePriceDocumentRepo websitePriceDocumentRepo) {
        this.websitePriceDocumentRepo = websitePriceDocumentRepo;
    }

    @Override
    public WebsitePriceDocument getDocumentById(int id) {
        return websitePriceDocumentRepo.findById(id).orElseThrow(
                new NoSuchDucumentException(strings.getString("websitePriceDocumentException"))
        );
    }

    @Override
    public List<WebsitePriceDocument> getAllDocuments() {
        return websitePriceDocumentRepo.findAll();
    }

    @Override
    public void createDocument(WebsitePriceDocument doc) {
        websitePriceDocumentRepo.save(doc);
        LOGGER.info(strings.getString("websitePriceDocumentCreation"));
    }

    @Override
    public void deleteDocument(int id) {
        websitePriceDocumentRepo.findById(id).ifPresentOrElse(doc -> {
                    websitePriceDocumentRepo.deleteById(id);
                    LOGGER.info(strings.getString("websitePriceDocumentDeletionSuccess") + doc.getId());
                }, () -> {
                    throw new NoSuchDucumentException(strings.getString("websitePriceDocumentDeletionFail") + id).get();
                }
        );
    }
}
