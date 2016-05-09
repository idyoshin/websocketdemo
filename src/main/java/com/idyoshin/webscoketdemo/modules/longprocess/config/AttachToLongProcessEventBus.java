package com.idyoshin.webscoketdemo.modules.longprocess.config;

import com.idyoshin.webscoketdemo.config.EventBusConfiguration;
import com.idyoshin.webscoketdemo.modules.longprocess.boundary.socket.LongProcessNotificationSocketBoundary;
import com.idyoshin.webscoketdemo.modules.longprocess.entity.LongProcessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import reactor.bus.EventBus;
import reactor.bus.selector.Selectors;

import javax.inject.Inject;

/**
 * attach the event bus
 */
@Configuration
@AutoConfigureAfter({EventBusConfiguration.class})
public class AttachToLongProcessEventBus implements ApplicationListener<ContextRefreshedEvent> {

    private static final Logger log = LoggerFactory.getLogger(AttachToLongProcessEventBus.class);

    @Inject
    private EventBus eventBus;

    @Inject
    private LongProcessNotificationSocketBoundary notificationSocketBoundary;


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.debug("attaching to LongLasting event bus");
        this.eventBus.on(Selectors.T(LongProcessNotificationQueue.class),
            o -> {
                LongProcessStatus status = (LongProcessStatus ) o.getData();
                notificationSocketBoundary.notifyOnProcessChange(status);
            });
    }
}

