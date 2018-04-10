package com.hb.core;


import com.hb.db.event.EventMetaDataDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ImportResource(locations = {
        "classpath:META-INF/event-config-bean-context.xml"
})
public class EngineApplicationContextLoader {

    private static Logger log = LoggerFactory.getLogger(EngineApplicationContextLoader.class.getSimpleName());

    public static void main(String[] args) {
        log.debug("START: initialize App Context for Engine ");
        SpringApplication.run(EngineApplicationContextLoader.class, args);
        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("META-INF/event-config-bean-context.xml");
        log.debug("Application Context initialized Successfully ");
    }


}
