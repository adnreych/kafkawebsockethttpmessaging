package com.example.mc1.service;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.repository.CircularMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Service
public class Ms1InteractionService {

    private final CircularMessageRepository circularMessageRepository;

    public Ms1InteractionService(CircularMessageRepository circularMessageRepository) {
        this.circularMessageRepository = circularMessageRepository;
    }


    public void startInteraction() {
        CircularMessage circularMessage = new CircularMessage();
        circularMessage.setMc1Timestamp(LocalDateTime.now());
        circularMessage = circularMessageRepository.save(circularMessage);
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
