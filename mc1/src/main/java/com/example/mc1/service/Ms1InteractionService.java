package com.example.mc1.service;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.repository.CircularMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class Ms1InteractionService {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final CircularMessageRepository circularMessageRepository;

    public Ms1InteractionService(CircularMessageRepository circularMessageRepository) {
        this.circularMessageRepository = circularMessageRepository;
    }


    public CircularMessage endInteraction(CircularMessage circularMessage) {
        circularMessage.setEndTimestamp(LocalDateTime.now());
        log.info("END INTERACTION WITH SESSION NUMBER " + circularMessage.getSessionId());
        return circularMessageRepository.save(circularMessage);
    }
}
