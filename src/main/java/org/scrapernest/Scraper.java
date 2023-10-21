package org.scrapernest;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

public class Scraper {

    private static final int scrapingIntervalMinutes = 60; //**UPDATE TO QUARTZ LATER**

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private ZonedDateTime dateOfCreation;

    @Getter
    @Setter
    private String targetUrl;

    @Getter
    @Setter
    private String scrapingParameters;

    @Getter
    @Setter
    private List<Item> scraperItems;

    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

}