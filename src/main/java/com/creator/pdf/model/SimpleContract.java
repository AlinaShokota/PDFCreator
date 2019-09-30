package com.creator.pdf.model;

import javax.persistence.Entity;
import java.time.LocalDate;

@Entity
public class SimpleContract extends Document {
    private LocalDate expirationDate;

    public SimpleContract(String companyName, String customerCompanyName, LocalDate expirationDate) {
        super(companyName, customerCompanyName);
        this.expirationDate = expirationDate;
    }

    public SimpleContract() {
    }


    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }
}
