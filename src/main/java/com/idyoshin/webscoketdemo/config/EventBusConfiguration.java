package com.idyoshin.webscoketdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.Environment;
import reactor.bus.EventBus;

/**
 * Simple event bus configuration
 */
@Configuration
public class EventBusConfiguration {

    private static final Logger log = LoggerFactory.getLogger(EventBusConfiguration.class);

    @Bean
    public Environment env() {
        return Environment.initializeIfEmpty()
            .assignErrorJournal();
    }

    @Bean
    public EventBus createEventBus(Environment env) {
        return EventBus.create(env, Environment.THREAD_POOL);
    }

}
