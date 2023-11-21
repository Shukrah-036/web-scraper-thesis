package com.scrapernest.webscraperthesis.repository;

import com.scrapernest.webscraperthesis.model.ScraperResult;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ScraperResultRepository extends MongoRepository<ScraperResult, String> {
}
