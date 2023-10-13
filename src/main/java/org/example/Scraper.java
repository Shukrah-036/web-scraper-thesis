package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class Scraper {

    private static final int scrapingIntervalMinutes = 60;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private Date dateOfCreation;

    @Getter
    @Setter
    private String targetUrl;

    @Getter
    @Setter
    private String scrapingParameters;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

}