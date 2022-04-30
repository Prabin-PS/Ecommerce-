package com.newproject.marketplace.service;

import com.newproject.marketplace.model.OutletSetup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class OutletSetupService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity outletconfig(OutletSetup outletSetup){
        int count = jdbcTemplate.update()
    }
}
