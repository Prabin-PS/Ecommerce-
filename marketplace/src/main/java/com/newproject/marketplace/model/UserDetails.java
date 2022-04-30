package com.newproject.marketplace.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserDetails {

    private Long   id;
    private String name;
    private String email;
    private String password;
    private String status;
    private String createdAt;
    private String updatedAt;

}
