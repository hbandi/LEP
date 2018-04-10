package com.hb.db.event;


import com.mongodb.MongoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
@ComponentScan("com.hb.db.event")
@ComponentScan("com.hb.query.spel")
@ComponentScan("com.hb.cache")
@EnableAutoConfiguration
public class MongoConfig {

    private Logger log = LoggerFactory.getLogger(MongoConfig.class.getSimpleName());

    private static String defaultCollectionName="eventValaMetaData";
    /**
     * TODO -- make this IP and Port as configurable .Load from application.properties file.
     * @return
     * @throws Exception
     */
    public @Bean
    MongoClient doMongoConfig() throws Exception {
        return new MongoClient("127.0.0.1", 27017);
    }

    public @Bean
    MongoTemplate getMongoTemplate() throws Exception {
        return new MongoTemplate(doMongoConfig(),defaultCollectionName);
    }

}
