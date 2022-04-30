package com.newproject.marketplace.controller;

import com.newproject.marketplace.model.UserDetails;
import com.newproject.marketplace.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/v{version:[1]}/login")
    public ResponseEntity userlogin(@RequestHeader String key){
        return loginService.userValidation(key);
    }

    @PostMapping("/v{version:[1]}/register")
    public ResponseEntity register(@RequestBody UserDetails userDetails){
        return loginService.register(userDetails);
    }
}
