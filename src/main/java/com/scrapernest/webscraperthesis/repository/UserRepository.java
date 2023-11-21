package com.scrapernest.webscraperthesis.repository;

import com.scrapernest.webscraperthesis.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
}

