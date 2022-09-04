package com.example.mc2.controller;

import com.example.mc2.model.CircularMessage;
import com.example.mc2.service.TransportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class MyWebSocketHandler extends TextWebSocketHandler {

    private Logger logger = LogManager.getLogger(MyWebSocketHandler.class);

    private final TransportService transportService;

    public MyWebSocketHandler(TransportService transportService) {
        this.transportService = transportService;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message)
            throws Exception {
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        CircularMessage circularMessage = mapper.readValue(message.getPayload(), CircularMessage.class);
        logger.info("circularMessage in mc2" + circularMessage.toString());
        transportService.send(circularMessage);
    }
}