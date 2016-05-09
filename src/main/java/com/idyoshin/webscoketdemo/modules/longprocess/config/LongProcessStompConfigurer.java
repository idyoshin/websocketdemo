package com.idyoshin.webscoketdemo.modules.longprocess.config;

import com.idyoshin.webscoketdemo.config.WebsocketConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Websocket configuration of Long-lasting processes
 */
@Configuration
public class LongProcessStompConfigurer implements WebsocketConfiguration.WebsocketStompEndpointConfigurer {

    private static final Logger log = LoggerFactory.getLogger(LongProcessStompConfigurer.class);

    public static final String LongProcessTopic = "/websocket/longprocess/instance";

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        log.info("Registering LongProcess websockets");
        registry
            .addEndpoint(LongProcessTopic)
            .withSockJS();
    }
}
