package com.creator.pdf.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class WebsitePriceDocument extends Document{

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "post_id")
    private List<PriceRow> pricingRows = new ArrayList<>();

    public WebsitePriceDocument(String companyName, String customerCompanyName, List<PriceRow> pricingRows) {
        super(companyName, customerCompanyName);
        this.pricingRows = pricingRows;
    }

    public WebsitePriceDocument() {
    }

    public List<PriceRow> getPricingRows() {
        return pricingRows;
    }
}
