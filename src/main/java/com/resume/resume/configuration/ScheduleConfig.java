package com.resume.resume.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfig {

    @Scheduled(fixedDelay = (1000 * 60 * 10)) // every 10 minutes make a call to the api
    public void scheduleFixedDelayTask() {
        String apiEndpoint = "https://ntobeko-malinga-backend.onrender.com/api/v1/skills";
        RestTemplate template = new RestTemplate();
        String results = template.getForObject(apiEndpoint, String.class);
        log.info("Called api " + results);
    }
}
