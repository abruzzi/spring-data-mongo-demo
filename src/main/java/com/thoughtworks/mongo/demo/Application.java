package com.thoughtworks.mongo.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.List;

@EnableMongoRepositories(basePackages = {"com.thoughtworks.mongo.*"})
public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class, MongoConfig.class);
        PersonRepository personRepository = context.getBean(PersonRepository.class);

        List<Person> all = personRepository.findAll();
        System.err.println(all);
    }

}
