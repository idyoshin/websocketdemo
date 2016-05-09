package com.idyoshin.webscoketdemo.modules.longprocess.controller;

import com.idyoshin.webscoketdemo.modules.longprocess.config.LongProcessNotificationQueue;
import com.idyoshin.webscoketdemo.modules.longprocess.entity.LongProcessStatus;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.bus.Event;
import reactor.bus.EventBus;


/**
 * demonstrational long-running process task
 */
public class LongProcessTask implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(LongProcessTask.class);

    private EventBus eventBus;

    private String id;
    private long maximumLevel;


    public LongProcessTask(String id, long maximumLevel, EventBus eventBus) {
        this.id = id;
        this.maximumLevel = maximumLevel;
        this.eventBus = eventBus;
    }


    @Override
    public void run() {
        long current = 0;

        try {
            while (current < maximumLevel) {
                eventBus.notify(LongProcessNotificationQueue.class, Event.wrap(new LongProcessStatus(id, current, maximumLevel)));
                long step = RandomUtils.nextLong(100l, maximumLevel / 10l);
                Thread.sleep(step);
                current+=step;
            }
        } catch (InterruptedException e) {
            log.debug("exception in thread.sleep ", e);
        }

        eventBus.notify(LongProcessNotificationQueue.class, Event.wrap(new LongProcessStatus(id, maximumLevel, maximumLevel)));
    }
}
