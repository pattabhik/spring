package com.pattabhi.rest_api.health;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomInfoIndicator implements InfoContributor {

    @Override
    public void contribute(Info.Builder builder) {
        Map<String, Object> customDetails = new HashMap<>();
        customDetails.put("app-description", "A demo Spring Boot application for managing students.");
        customDetails.put("maintainer", "Pattabhi");
        customDetails.put("contact-email", "contact@example.com");
        customDetails.put("version", "1.0.0");

        builder.withDetail("customInfo", customDetails);
    }
}