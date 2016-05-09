package com.idyoshin.webscoketdemo.modules.longprocess.boundary.socket;

import com.idyoshin.webscoketdemo.modules.longprocess.config.LongProcessStompConfigurer;
import com.idyoshin.webscoketdemo.modules.longprocess.entity.LongProcessStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

/**
 * Notification over websocket on events of process
 */
@Controller
public class LongProcessNotificationSocketBoundary {

    private static final Logger log = LoggerFactory.getLogger(LongProcessNotificationSocketBoundary.class);

    @Inject
    private SimpMessagingTemplate simpMessagingTemplate;

    public void notifyOnProcessChange(LongProcessStatus status) {
        log.debug("Notifying client on changes in process {}", status);
        simpMessagingTemplate
            .convertAndSend(
                LongProcessStompConfigurer.LongProcessTopic + "/" + status.getId(),
                status
            );
    }
}
