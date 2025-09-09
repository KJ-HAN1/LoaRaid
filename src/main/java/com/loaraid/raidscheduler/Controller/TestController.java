package com.loaraid.raidscheduler.Controller;

import com.loaraid.raidscheduler.EnvConfig;
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
