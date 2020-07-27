package com.productmanagement.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Product")
public class ProductModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name = "ID")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Price")
    private double price;

    @Column(name = "Description")
    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "ManufacturerID")
    private ManufacturerModel manufacturer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ManufacturerModel getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(ManufacturerModel manufacturer) {
        this.manufacturer = manufacturer;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
