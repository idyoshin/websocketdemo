package com.idyoshin.webscoketdemo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * Simple thread-pool configuration
 */
@Configuration
public class LongProcessThreadPoolConfiguration {

    private static final Logger log = LoggerFactory.getLogger(LongProcessThreadPoolConfiguration.class);

    public static final String NAME = "threadPoolForLongProcesses";

    @Bean(name = NAME)
    public ThreadPoolTaskExecutor threadPoolTaskExecutor() {
        log.debug("configuring the threadpool for long-lasting processes");
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(25);
        executor.setMaxPoolSize(50);

        return executor;
    }
}
