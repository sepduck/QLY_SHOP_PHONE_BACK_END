package com.qlyshopphone_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity

public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "supplier_id")
    private int supplierId;

    @Column(name = "supplier_name")
    private String supplierName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "address")
    private String address;

    @Column(name = "email")
    private String email;

    @Column(name = "company")
    private String company;

    @Column(name = "tax_code")
    private String taxCode;

    @Column(name = "active")
    private byte active;

    @ManyToOne
    @JoinColumn(name = "group_supplier_id")
    private GroupSupplier groupSupplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
