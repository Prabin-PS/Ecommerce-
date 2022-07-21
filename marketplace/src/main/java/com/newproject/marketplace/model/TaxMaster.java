package com.newproject.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TaxMaster {

    private Long id;
    private String taxName;
    private String status;
    private String createdAt;
    private String updatedAt;
    private Long outletId;
}
