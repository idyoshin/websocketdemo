package com.idyoshin.webscoketdemo.modules.longprocess.controller;

import com.idyoshin.webscoketdemo.config.LongProcessThreadPoolConfiguration;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Controller;
import reactor.bus.EventBus;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Demonstrational long lasting process execution
 */
@Controller
public class LongProcessLauncher {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LongProcessLauncher.class);


    @Inject
    @Qualifier(value = LongProcessThreadPoolConfiguration.NAME)
    private ThreadPoolTaskExecutor taskExecutor;

    @Inject
    private EventBus eventBus;


    /**
     * Start single instance of the job and returns the String id of it
     *
     * @return
     */
    public String launchJob() {
        String id = UUID.randomUUID().toString();
        taskExecutor.execute(new LongProcessTask(id, RandomUtils.nextLong(5000l, 15000l), eventBus), RandomUtils.nextLong(2000l, 5000l));
        return id;
    }

    public List<String> launchJobs(int jobsNumber) {
        return IntStream
            .range(0, jobsNumber)
            .mapToObj(o -> this.launchJob())
            .collect(Collectors.toList());
    }
}
