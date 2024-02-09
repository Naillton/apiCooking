package com.nailton.apiCooking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;

import java.util.UUID;

@Entity
@Transactional
@Table(name = "revenues")
public class Revenues {

    @Id
    private final UUID id;

    private String title;

    private String products;

    private String description;

    public Revenues() {
        this.id = UUID.randomUUID();
    }

    public Revenues(String title, String products, String description) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.products = products;
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
