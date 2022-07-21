package com.newproject.marketplace.controller;

import com.newproject.marketplace.model.CategoryMaster;
import com.newproject.marketplace.model.GetCategoryMaster;
import com.newproject.marketplace.model.GetItemMaster;
import com.newproject.marketplace.service.CategoryMasterService;
import com.newproject.marketplace.service.ItemMasterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryMasterController {

    @Autowired
    private CategoryMasterService categoryMasterService;

    @PostMapping("/v{version:[1]}/category-master")
    public ResponseEntity catgeorycreation(@RequestBody CategoryMaster categoryMaster){
        return categoryMasterService.updatecategorymaster(categoryMaster);
    }
    @GetMapping("/get/v{version:[1]}/category-master")
    public ResponseEntity getcategorymaster(@RequestBody GetCategoryMaster getCategoryMaster){
        return categoryMasterService.getcategorymaster(getCategoryMaster);

    }


}


