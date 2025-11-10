package com.socialv2.okr.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheckController {
    @GetMapping("/ping")
    @ResponseBody
    public String healthCheck() {
        return "OKR Backend is running!";
    }
}
