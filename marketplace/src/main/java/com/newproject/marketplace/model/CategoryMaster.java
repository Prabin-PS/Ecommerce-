package com.newproject.marketplace.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryMaster {

    private Long id;
    private String categoryName;
    private String alias;
    private String status;
    private String createdAt;
    private String updatedAt;
    private Long outletId;

}
