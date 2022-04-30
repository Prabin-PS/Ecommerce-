package com.newproject.marketplace.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class OutletSetup {

    private Long id;
    private String outlet_name;
    private String url;
    private String status;
    private String created_at;
    private String updated_at;
}
