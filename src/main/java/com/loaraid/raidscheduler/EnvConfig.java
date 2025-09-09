package com.loaraid.raidscheduler;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvConfig {
    private final Dotenv dotenv;
    private final String apikey;

    public EnvConfig(){
        dotenv = Dotenv.load();
        apikey = dotenv.get("API_KEY");
    }
    public String getApikey() {
        return apikey;
    }
}
