package com.loaraid.raidscheduler.config;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {
    private final Dotenv dotenv;
    private final String apikey;
    private final String defaultURL;

    public EnvConfig(){
        dotenv = Dotenv.load();
        apikey = dotenv.get("API_KEY");
        defaultURL = dotenv.get("API_URL");
    }
    public String defaultURL() { return defaultURL; }
    public String getApikey() {
        return apikey;
    }
}
