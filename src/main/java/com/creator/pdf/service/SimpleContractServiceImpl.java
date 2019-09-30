package com.creator.pdf.service;

import com.creator.pdf.model.SimpleContract;
import com.creator.pdf.repository.SimpleContractRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleContractServiceImpl implements DocumentService<SimpleContract> {

    private SimpleContractRepo simpleContractRepo;

    public SimpleContractServiceImpl(SimpleContractRepo simpleContractRepo) {
        this.simpleContractRepo = simpleContractRepo;
    }

    @Override
    public SimpleContract getDocumentById(int id) {
        return simpleContractRepo.findById(id).get();
    }

    @Override
    public List<SimpleContract> getAllDocuments() {
        return simpleContractRepo.findAll();
    }

    @Override
    public void createDocument(SimpleContract doc) {
        simpleContractRepo.save(doc);
    }
}
