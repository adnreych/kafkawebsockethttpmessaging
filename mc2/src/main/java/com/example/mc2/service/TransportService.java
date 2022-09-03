package com.example.mc2.service;

import com.example.mc2.model.CircularMessage;
import com.example.mc2.repository.CircularMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class TransportService {

    private Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    private final KafkaTemplate<Long, CircularMessage> kafkaCircularMessageTemplate;
    private final ObjectMapper objectMapper;
    private final CircularMessageRepository circularMessageRepository;

    @Autowired
    public TransportService(KafkaTemplate<Long, CircularMessage> kafkaCircularMessageTemplate,
                            ObjectMapper objectMapper, CircularMessageRepository circularMessageRepository) {
        this.kafkaCircularMessageTemplate = kafkaCircularMessageTemplate;
        this.objectMapper = objectMapper;
        this.circularMessageRepository = circularMessageRepository;
    }



    public void send(CircularMessage circularMessage) {
        circularMessage.setMc2Timestamp(LocalDateTime.now());
        circularMessage = circularMessageRepository.save(circularMessage);
        log.info("<= sending {}", circularMessage.toString());
        kafkaCircularMessageTemplate.send("server.circularmessage", circularMessage);
    }

}

