package com.newproject.marketplace.controller;

import com.newproject.marketplace.model.TaxMaster;
import com.newproject.marketplace.service.TaxMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TaxMasterController {

    @Autowired
    private TaxMasterService taxMasterService;

    @PostMapping("/v{version:[1]}/tax-master")
    public ResponseEntity taxmaster (@RequestBody TaxMaster taxMaster){
        return taxMasterService.updatetaxmaster(taxMaster);
    }

}
