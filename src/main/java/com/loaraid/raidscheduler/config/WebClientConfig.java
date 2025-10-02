package com.loaraid.raidscheduler.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {
    private EnvConfig env;

    WebClientConfig() {
        env = new EnvConfig();
    }

    @Bean
    public WebClient loaWebClient(){
        return WebClient.builder()
                .baseUrl(env.getApikey()) // 기본 API URL
                .defaultHeader("Authorization", "Bearer " + System.getenv("API_KEY"))
                .build();
    }
}
