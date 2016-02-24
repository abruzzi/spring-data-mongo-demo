package com.thoughtworks.mongo.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.thoughtworks.mongo.*"})
public class MongoConfig {
    @Autowired
    PersonRepository personRepository;
}
