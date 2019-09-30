package com.creator.pdf.model;

import javax.persistence.*;
import java.time.LocalDate;


@MappedSuperclass
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    LocalDate current;
    String companyName;
    String customerCompanyName;

    public Document(String companyName, String customerCompanyName) {
        this.companyName = companyName;
        this.customerCompanyName = customerCompanyName;
        this.current = LocalDate.now();
    }

    public Document() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getCurrent() {
        return current;
    }

    public void setCurrent(LocalDate current) {
        this.current = current;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomerCompanyName() {
        return customerCompanyName;
    }

    public void setCustomerCompanyName(String customerCompanyName) {
        this.customerCompanyName = customerCompanyName;
    }
}
