package com.thoughtworks.mongo.demo;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = {"com.thoughtworks.mongo.*"})
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Autowired
    PersonRepository personRepository;

    @Value("${mongo.host:localhost}")
    private String mongoHost;

    @Value("${mongo.database:mydb}")
    private String mongoDatabase;

    @Autowired
    private Environment environment;

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        System.err.println(environment);

        System.err.println(mongoHost);
        System.err.println(mongoDatabase);

        return new SimpleMongoDbFactory(new MongoClient("localhost"), "test");
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongoDbFactory());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
