package com.scrapernest.webscraperthesis.model;

import com.scrapernest.webscraperthesis.model.Item;
import com.scrapernest.webscraperthesis.model.Scraper;
import com.scrapernest.webscraperthesis.model.ScraperResult;
import com.scrapernest.webscraperthesis.repository.ItemRepository;
import com.scrapernest.webscraperthesis.repository.ScraperRepository;
import com.scrapernest.webscraperthesis.repository.ScraperResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.Getter;
import lombok.Setter;
import org.tinylog.Logger;

import java.util.List;

@Component
public class ScraperController {

    @Autowired
    private ScraperRepository scraperRepository;

    @Autowired
    private ScraperResultRepository scraperResultRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Setter
    @Getter
    private String scraperName = "Scraper_JapaneseKnives_Test";

    private Scraper scraper = new Scraper();

    public void scrapeAndSaveResults() {
        Logger.info("Starting scraping process...");
        scraper.setName(scraperName);

        List<Item> scraperItems = List.of(
            Item.builder()
                .selector("#article_83708816 > span.row-bottom > div > div > span > span.action")
                .label("Price")
                .build()
        );

        scraper.setScraperItems(scraperItems);
        scraper.setScraperResults(List.of());
        scraper.execute();

        for (ScraperResult scraperResult : scraper.getScraperResults()) {

            Item savedItem = itemRepository.save(scraperResult.getAssociatedItem());
            scraperResult.setAssociatedItem(savedItem);

            scraperResultRepository.save(scraperResult);

            ScraperResult existingResult = scraperResultRepository.findById(scraperResult.getId()).orElse(null);
            if (existingResult != null && hasResultChanged(existingResult, scraperResult)) {
                Logger.info("Scrape result has changed!");
            } else {
                Logger.info("Scrape result has not changed.");
            }
        }
        scraperRepository.save(scraper);
        Logger.info("Scraping process completed.");
    }


    private boolean hasResultChanged(ScraperResult existingResult, ScraperResult newResult) {
        return !existingResult.getScraperName().equals(newResult.getScraperName())
                || !existingResult.getAssociatedItem().getLabel().equals(newResult.getAssociatedItem().getLabel());
    }

}
