package com.creator.pdf.service;

import com.creator.pdf.model.Document;

import java.util.List;

public interface DocumentService<T extends Document> {
    T getDocumentById(int id);
    List<T> getAllDocuments();
    void createDocument(T doc);
}
