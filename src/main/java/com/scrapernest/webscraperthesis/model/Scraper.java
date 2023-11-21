package com.scrapernest.webscraperthesis.model;

import lombok.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tinylog.Logger;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "scrapers")
public class Scraper {

    private static final int scrapingIntervalMinutes = 60; //**UPDATE TO QUARTZ LATER**

    @Id
    private String id;

    @Getter
    @Setter
    private String name;

    @Getter
    @Setter
    private String targetUrl = "https://www.japaneseknives.eu/";

    @Getter
    @Setter
    private String scrapingParameters;

    @Getter
    @Setter
    private List<Item> scraperItems;

    @Getter
    @Setter
    private List<ScraperResult> scraperResults;

    @Getter
    @Setter
    private boolean success;

    public void execute() {

        try {
            Logger.info("Executing scraper for: {}", name);
            for (Item item : scraperItems) {
                Logger.info("Scraping data for item: {}", item.getLabel());
                org.jsoup.nodes.Document doc = Jsoup.connect(targetUrl).get();

                Logger.debug(doc.title());
                List<String> extractedData = new ArrayList<>();

                Elements elements = doc.select(item.getSelector());
                Logger.debug(elements);
                for (Element element : elements) {
                    Logger.debug(element);
                    String data = element.text();
                    extractedData.add(data);
                }
                ScraperResult scraperResult = ScraperResult.builder()
                        .scraperName(name)
                        .timeStamp(LocalDateTime.now())
                        .extractedData(extractedData)
                        .associatedItem(item)
                        .success(!extractedData.isEmpty())
                        .build();

                scraperResults.add(scraperResult);
                Logger.info("Execution completed successfully.");
            }
            Logger.debug("Executed :)");
        } catch (IOException e) {

            Logger.error("Error occurred during web scraping: {}", e.getMessage(), e);
        }
    }

}