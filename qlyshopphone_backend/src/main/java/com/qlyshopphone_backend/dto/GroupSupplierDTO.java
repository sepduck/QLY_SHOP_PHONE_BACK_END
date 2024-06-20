package com.qlyshopphone_backend.dto;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupSupplierDTO {
    private String  groupSupplierName;
    private String  note;
}
