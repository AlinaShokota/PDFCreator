package com.creator.pdf.service;

import com.creator.pdf.model.WebsitePriceDocument;
import com.creator.pdf.repository.WebsitePriceDocumentRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class SimpleContractServiceImplTest {
    @Mock
    WebsitePriceDocumentRepo websitePriceDocumentRepo;

    @InjectMocks
    WebsitePriceDocumentServiceImpl websitePriceDocumentService
            = new WebsitePriceDocumentServiceImpl(websitePriceDocumentRepo);

    @Test
    public void testGetDocById() {
        Optional<WebsitePriceDocument> websitePriceDocument = Optional.of(
                new WebsitePriceDocument("Test", "Test", Collections.emptyList()));
        Optional<WebsitePriceDocument> websitePriceDocumentWithId5 = Optional.of(
                new WebsitePriceDocument("Five", "Five", Collections.emptyList()));

        doReturn(websitePriceDocument).when(websitePriceDocumentRepo).findById(anyInt());
        assertEquals("Test", websitePriceDocumentService.getDocumentById(3).getCompanyName());
        verify(websitePriceDocumentRepo, times(1)).findById(3);

        when(websitePriceDocumentRepo.findById(5)).thenReturn(websitePriceDocumentWithId5);
        assertEquals("Five", websitePriceDocumentService.getDocumentById(5).getCustomerCompanyName());
        verify(websitePriceDocumentRepo).findById(5);
    }

    @Test
    public void testGetAllDocuments() {
        List<WebsitePriceDocument>websitePriceDocuments = Collections.emptyList();
        when(websitePriceDocumentRepo.findAll()).thenReturn(websitePriceDocuments);
        websitePriceDocumentService.getAllDocuments();
        websitePriceDocumentService.getAllDocuments();
        verify(websitePriceDocumentRepo, times(2)).findAll();
    }

    @Test(expected = Exception.class)
    public void testCreateDocument(){
        WebsitePriceDocument websitePriceDocument = new WebsitePriceDocument();
        doThrow(new RuntimeException()).when(websitePriceDocumentRepo).save(websitePriceDocument);
        websitePriceDocumentService.createDocument(websitePriceDocument);
    }
}