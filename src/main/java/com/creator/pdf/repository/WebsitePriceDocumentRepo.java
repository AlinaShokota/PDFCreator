package com.creator.pdf.repository;

import com.creator.pdf.model.WebsitePriceDocument;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WebsitePriceDocumentRepo extends JpaRepository<WebsitePriceDocument, Integer> {
}
