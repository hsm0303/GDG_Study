package com.gdghongik.commerce.presentation;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeployController {

    @GetMapping("/deploy-check")
    public String deployCheck() {
        return "수하 누나 사랑해";
    }
}
