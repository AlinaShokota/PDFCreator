package com.creator.pdf.repository;

import com.creator.pdf.model.SimpleContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimpleContractRepo extends JpaRepository<SimpleContract, Integer> {
}
