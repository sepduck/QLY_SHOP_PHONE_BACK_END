package com.qlyshopphone_backend.dto;

import com.qlyshopphone_backend.model.GroupProduct;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ProductDTO {
    private String productName;
    private float price;
    private float capitalPrice;
    private float inventory;
    private int groupProductId;
    private int trademarkId;
    private int locationId;
    private float weight;
    private int propertiesId;
    private int directSales;
    private int categoryId;
    private int unitId;
    private int active;
}
