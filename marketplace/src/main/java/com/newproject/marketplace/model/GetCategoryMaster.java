package com.newproject.marketplace.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetCategoryMaster {
    private Long id = 0L;
    private Long outletId = 0L;
    private String status = "";

}
