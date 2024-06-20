package com.qlyshopphone_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private float price;

    @Column(name = "capital_price")
    private float capitalPrice;

    @Column(name = "inventory")
    private float inventory;

    @ManyToOne
    @JoinColumn(name = "group_product_id")
    private GroupProduct groupProduct;

    @ManyToOne
    @JoinColumn(name = "trademark_id")
    private Trademark trademark;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @Column(name = "weight")
    private float weight;

    @Column(name = "active")
    private int active;

    @Column(name = "direct_sales")
    private int directSales;

    @ManyToOne
    @JoinColumn(name = "properties_id")
    private Properties properties;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private Unit unit;
}
