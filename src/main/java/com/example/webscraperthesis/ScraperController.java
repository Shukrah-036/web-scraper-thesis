package com.example.webscraperthesis;

import Results.JsonResultRepository;
import Results.ResultRepository;
import Results.ScraperResult;
import lombok.Getter;
import lombok.Setter;
import org.scrapernest.Item;
import org.scrapernest.Scraper;
import org.tinylog.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

public class ScraperController {

    private ResultRepository resultRepository = new JsonResultRepository(Path.of("results.json"));

    @Setter
    @Getter
    private String scraperName = "Scraper_JapaneseKnives_Test";

    @Getter
    @Setter
    private Item associatedItem = new Item();

    public String scrapeAndSaveResults() {
        Scraper scraper = new Scraper();
        String scrapingParameters = "#article_83708816 > span.row-bottom > div > div > span > span.action";
        associatedItem.setSelector(scrapingParameters);
        associatedItem.setLabel("Price");
        scraper.execute(scrapingParameters);

        try {
            List<ScraperResult> existingResults = resultRepository.getAll();
            ScraperResult scraperResult = createScraperResult(scraper);

            if (hasResultChanged(existingResults, scraperResult)) {
                Logger.info("Scrape result has changed!");
            } else {
                Logger.info("Scrape result has not changed.");
            }

            saveResult(scraperResult);
        } catch (IOException e) {
            Logger.error("Error occurred while getting existing results: {}", e.getMessage(), e);
        }

        return "scraping-result";
    }

    private ScraperResult createScraperResult(Scraper scraper) {
        return ScraperResult.builder()
                .scraperName(scraperName)
                .timeStamp(LocalDateTime.now())
                .extractedData(scraper.getExtractedData())
                .associatedItem(associatedItem)
                .success(scraper.isSuccess())
                .build();
    }

    private void saveResult(ScraperResult scraperResult) {
        try {
            resultRepository.add(scraperResult);
        } catch (IOException e) {
            Logger.error("Error occurred while saving result: {}", e.getMessage(), e);
        }
    }

    private boolean hasResultChanged(List<ScraperResult> existingResults, ScraperResult newResult) {
        for (ScraperResult existingResult : existingResults) {
            if (existingResult.getScraperName().equals(newResult.getScraperName())
                    && existingResult.getExtractedData().equals(newResult.getExtractedData())) {
                return false;
            }
        }
        return true;
    }
}
