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
        try {
        int count = jdbcTemplate.update("update outlet_details set outlet_name=?, url=?, status=?, updated_at=current_timestamp " +
                "where id = ?", outletSetup.getOutlet_name(), outletSetup.getUrl(), outletSetup.getStatus(), outletSetup.getId());
        if(count==0){
             jdbcTemplate.update("insert into outlet_details(outlet_name,url,status,created_at,updated_at)" +
                     "values(?,?,?,current_timestamp , current_timestamp )", outletSetup.getOutlet_name(), outletSetup.getUrl(), outletSetup.getStatus() );
            return ResponseEntity.ok("Outlet created successfully");
        }
            return ResponseEntity.ok("Outlet updated Successfully..!!");
    }catch(Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok("Unable to register outlet..!");
        }
    }
}
