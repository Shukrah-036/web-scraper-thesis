package org.scrapernest;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.tinylog.Logger;

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
    private String targetUrl = "https://www.japaneseknives.eu/";

    @Getter
    @Setter
    private String scrapingParameters = "#article_83708816 > span.row-bottom > div > div > span > span.action";

    @Getter
    @Setter
    private List<Item> scraperItems;

    @Getter
    @Setter
    private List<String> extractedData;

    @Getter
    @Setter
    private boolean success;
    private boolean isActive;

    public boolean isActive() {
        return isActive;
    }

    public void execute(String scrapingParameters) {

        try {
            Document doc = Jsoup.connect(targetUrl).get();

            Logger.debug(doc.title());
            extractedData = new ArrayList<>();

            Elements elements = doc.select(scrapingParameters);
            Logger.debug(elements);
            for (Element element : elements) {
                Logger.debug(element);
                String data = element.text();
                extractedData.add(data);
            }
            if (!extractedData.isEmpty()){
                success = true;
            }
            Logger.debug("Executed :-)");
        }
        catch (IOException e) {

            Logger.error("Error occurred during web scraping: {}", e.getMessage(), e);
        }
    }

}