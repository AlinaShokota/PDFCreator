package com.creator.pdf.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "price_row")
public class PriceRow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String description;

    private int price;

    public PriceRow(String title, String description, int price) {
        this.title = title;
        this.description = description;
        this.price = price;
    }

    public PriceRow() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }
}
