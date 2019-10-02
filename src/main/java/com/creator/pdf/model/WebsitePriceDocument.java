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

    private int cost = 0 ;

    public WebsitePriceDocument(String companyName, String customerCompanyName, List<PriceRow> pricingRows) {
        super(companyName, customerCompanyName);
        this.pricingRows = pricingRows;
        this.cost = calculateCost();
    }

//    public WebsitePriceDocument(String companyName, String customerCompanyName, List<PriceRow> pricingRows, int cost) {
//        super(companyName, customerCompanyName);
//        this.pricingRows = pricingRows;
//        this.cost = calculateCost();
//    }

    public WebsitePriceDocument() {
    }

    public List<PriceRow> getPricingRows() {
        return pricingRows;
    }

    public void setPricingRows(List<PriceRow> pricingRows) {
        this.pricingRows = pricingRows;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    private int calculateCost(){
        int cost = 0;
        for (PriceRow pricingRow : pricingRows) {
            cost+=pricingRow.getPrice();
        }
        return cost;
    }
}
