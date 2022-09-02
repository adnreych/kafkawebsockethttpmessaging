package com.example.mc1.service;

import com.example.common.model.CircularMessage;
import com.example.common.model.ServiceName;
import com.example.common.service.InteractionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@Service
public class Ms1InteractionService {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final InteractionService interactionService;


    public Ms1InteractionService(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    public void startInteraction() {
        CircularMessage circularMessage = new CircularMessage();
        interactionService.provideInteraction(ServiceName.MS1, circularMessage);
        try {
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8083/socketHandler"));
            ObjectMapper mapper = JsonMapper.builder()
                    .addModule(new JavaTimeModule())
                    .build();
            String json = mapper.writeValueAsString(circularMessage);
            clientEndPoint.sendMessage(json);

        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }
}
