package com.loaraid.raidscheduler.controller;

import com.loaraid.raidscheduler.config.EnvConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/test")
public class TestController {
    EnvConfig envConfig;

    TestController(EnvConfig envConfig) {
        this.envConfig = envConfig;
    }

    @GetMapping
    public String test() {
        System.out.println("test");
        return envConfig.getApikey();
    }
}
