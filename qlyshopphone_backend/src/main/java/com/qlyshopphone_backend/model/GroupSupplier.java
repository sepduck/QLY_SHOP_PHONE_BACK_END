package com.qlyshopphone_backend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class GroupSupplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_supplier_id")
    private int groupSupplierId;

    @Column(name = "group_supplier_name")
    private String  groupSupplierName;

    @Column(name = "note")
    private String  note;
}
