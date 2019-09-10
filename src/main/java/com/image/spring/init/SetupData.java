package com.image.spring.init;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class SetupData implements ApplicationListener<ContextRefreshedEvent> {
    private static Logger logger = Logger.getLogger(SetupData.class);
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        try {
            Files.createDirectory(Paths.get("images"));
        } catch (IOException e) {
            logger.error("Directory created Error " + e);
        }
    }
}
