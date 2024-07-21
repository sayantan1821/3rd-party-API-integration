package com.sayantan.productservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseRecord {
    private String title;
    private double price;
    private String description;
    @ManyToOne
    private Category category;
    private String image;
}
