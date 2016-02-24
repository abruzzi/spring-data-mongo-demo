package com.thoughtworks.mongo.demo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.thoughtworks.mongo.*"})
@ComponentScan(basePackages = {"com.thoughtworks.mongo.*"})
@PropertySource("classpath:application.properties")
public class MongoConfig {
    @Autowired
    PersonRepository personRepository;

    @Value("${mongo.host:localhost}")
    private String mongoHost;

    @Value("${mongo.database:mydb}")
    private String mongoDatabase;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        System.err.println(mongoHost);
        System.err.println(mongoDatabase);

        return new SimpleMongoDbFactory(new MongoClient(mongoHost), mongoDatabase);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigInDev() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
