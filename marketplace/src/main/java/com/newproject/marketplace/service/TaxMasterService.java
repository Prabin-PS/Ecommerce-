package com.newproject.marketplace.service;

import com.newproject.marketplace.model.TaxMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaxMasterService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity updatetaxmaster(TaxMaster taxMaster){
     try {
         int count = jdbcTemplate.update("update tax_master set tax_name=?, status = ?, updated_at = current_timestamp " +
                 "where id = ? and outlet_id = ?", taxMaster.getTaxName(), taxMaster.getStatus(), taxMaster.getId(), taxMaster.getOutletId());

         if (count == 0) {
             jdbcTemplate.update("insert into tax_master (tax_name, status, id, outlet_id, created_at, updated_at) values" + " (?,?,?,?, current_timestamp," +
                     " current_timestamp )", taxMaster.getTaxName(), taxMaster.getStatus(), getId(taxMaster.getOutletId()), taxMaster.getOutletId());
             return ResponseEntity.ok("Tax Master created successfully");
         }
         return ResponseEntity.ok("Tax Master updated Successfully");
     }
     catch (Exception e){
         e.printStackTrace();
         return ResponseEntity.ok("Unable create tax master. Kindly check");
     }
    }
    private Long getId(Long outlet_id) {
        String query = "INSERT INTO tax_master_sequence (outlet_id,tax_master_id) values ( ?, 1) on conflict (outlet_id) " +
                "do update set tax_master_id = tax_master_sequence.tax_master_id + 1";
        jdbcTemplate.update(query,outlet_id);
        return jdbcTemplate.queryForObject("select tax_master_id from tax_master_sequence where outlet_id = ?"  , Long.class,outlet_id);
    }
}
