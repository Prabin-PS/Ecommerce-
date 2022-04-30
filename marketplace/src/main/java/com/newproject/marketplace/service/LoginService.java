package com.newproject.marketplace.service;

import com.newproject.marketplace.model.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

@Service
public class LoginService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public ResponseEntity userValidation(String key) {
        List<String> details = Arrays
                .asList(new String(Base64
                        .getDecoder()
                        .decode(key
                                .getBytes(StandardCharsets.UTF_8))).split(":"));
        SqlRowSet sqlRowSet = jdbcTemplate.queryForRowSet("select * from user_details where email = ? and password = ? " +
                "and status='Active'",details.get(0),details.get(1));
        if(sqlRowSet.next())
        {
         return ResponseEntity.ok("Login Successfull");
        }else{
            return ResponseEntity.ok("Invalid Login..Kindly Register");
        }
    }

    public ResponseEntity register(UserDetails userDetails){
        try {
            int count = jdbcTemplate.update("update user_details set name = ?, status = ?, updatedat= current_timestamp " +
                    "where email=? and password=?", userDetails.getName(), userDetails.getStatus(),
                    userDetails.getEmail(),userDetails.getPassword());
            if (count == 0) {
                jdbcTemplate.update("insert into user_details (name,email,password,status,createdat,updatedat) " +
                        "values(?,?,?,?,current_timestamp ,current_timestamp) ", userDetails.getName(),
                        userDetails.getEmail(), userDetails.getPassword(), userDetails.getStatus());
            }
            return ResponseEntity.ok("Registeration Successful..!!");
        }
        catch(Exception e){
            e.printStackTrace();
            return ResponseEntity.ok("Unable to Register");
        }

    }






    /*
    *  queryForList
    * query
    * queryForObject
    * 
    * */
    
    
}
