package com.scrapernest.webscraperthesis.repository;

import com.scrapernest.webscraperthesis.model.Scraper;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScraperRepository extends MongoRepository<Scraper, String> {
}
