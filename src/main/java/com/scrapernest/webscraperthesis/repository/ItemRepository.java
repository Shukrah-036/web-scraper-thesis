package com.scrapernest.webscraperthesis.repository;

import com.scrapernest.webscraperthesis.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, String> {
}

