package com.newproject.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemMaster {

    private Long id;
    private String itemName;
    private String alias;
    private Double mrp;
    private Double saleRate;
    private Double stock;
    private Double gst;
    private String Category;
    private Boolean taxType;
    private String status;
    private String createdAt;
    private String updatedAt;
    private Long outletId;

}
