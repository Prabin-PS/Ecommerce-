package com.newproject.marketplace.controller;

import com.newproject.marketplace.model.OutletSetup;
import com.newproject.marketplace.service.OutletSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class OutletSetupController {

    @Autowired
    private OutletSetupService outletSetupService;

    @PostMapping("/v{version:[1]}/outlet-setup")
    public ResponseEntity outletsetup(@RequestBody OutletSetup outletSetup){
        return outletSetupService.outletconfig(outletSetup);
    }


}
