package com.newproject.marketplace.common;

import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class CommonUtils {

    public String base64Decode(String key){
        return new String(Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8)));
        }

}
