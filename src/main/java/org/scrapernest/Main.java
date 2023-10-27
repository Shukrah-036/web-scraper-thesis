package org.scrapernest;

import com.example.webscraperthesis.ScraperController;

public class Main {
    public static void main(String[] args) {
        ScraperController scraperController = new ScraperController();
        scraperController.scrapeAndSaveResults();
    }
}