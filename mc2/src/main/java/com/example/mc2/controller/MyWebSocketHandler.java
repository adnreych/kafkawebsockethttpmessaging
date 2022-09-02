package com.example.mc2.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private Logger logger = LogManager.getLogger(MyWebSocketHandler.class);

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        logger.info("fgsdfgdf" + message.getPayload());
    }
}