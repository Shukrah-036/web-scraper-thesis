package com.scrapernest.webscraperthesis.scraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan(basePackages = "com.scrapernest.webscraperthesis.model")
@SpringBootApplication
public class WebScraperThesisApplication {

	public static void main(String[] args) {
		SpringApplication.run(WebScraperThesisApplication.class, args);
	}

}
