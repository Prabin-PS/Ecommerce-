package com.newproject.marketplace.controller;

import com.newproject.marketplace.model.GetItemMaster;
import com.newproject.marketplace.model.ItemMaster;
import com.newproject.marketplace.service.ItemMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ItemMasterController {

    @Autowired
    private ItemMasterService itemMasterService;


    @PostMapping("/v{version:[1]}/item-master")
    public ResponseEntity itemmaster(@RequestBody ItemMaster itemMaster){
        return itemMasterService.updateitemmaster(itemMaster);
    }

    @GetMapping("/get/v{version:[1]}/item-master")
    public ResponseEntity getitemmaster(@RequestBody GetItemMaster getItemMaster){
        return itemMasterService.getitemmaster(getItemMaster);

    }

}
