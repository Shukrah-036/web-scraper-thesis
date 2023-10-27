package com.example.webscraperthesis;

import Results.JsonResultRepository;
import Results.ResultRepository;
import Results.ScraperResult;
import lombok.Getter;
import lombok.Setter;
import org.scrapernest.Item;
import org.scrapernest.Scraper;
import org.springframework.web.bind.annotation.GetMapping;
import org.tinylog.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

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


        ScraperResult scraperResult = createScraperResult(scraper);
        saveResult(scraperResult);

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
}
