package com.example.mc1.service;

import com.example.common.model.CircularMessage;
import com.example.common.model.ServiceName;
import com.example.common.service.InteractionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@Service
public class Ms1InteractionService {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final InteractionService interactionService;


    public Ms1InteractionService(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    public void startInteraction() {
        log.info("STAAARTED");
        CircularMessage circularMessage = new CircularMessage();
        interactionService.provideInteraction(ServiceName.MS1, circularMessage);
        WebSocketClient client = new StandardWebSocketClient();

        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        MappingJackson2MessageConverter mappingJackson2MessageConverter = new MappingJackson2MessageConverter();
        mappingJackson2MessageConverter.setObjectMapper(mapper);
        stompClient.setMessageConverter(mappingJackson2MessageConverter);

        StompSessionHandler sessionHandler = new MyStompSessionHandler();
        try {
            StompSession stompSession = stompClient.connect("ws://localhost:8083/websocket", sessionHandler).get();
            stompSession.send("/chat.addUser", circularMessage);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }
}
