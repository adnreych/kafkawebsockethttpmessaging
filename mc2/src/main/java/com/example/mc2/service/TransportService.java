package com.example.mc2.service;

import com.example.mc2.model.CircularMessage;
import com.example.mc2.repository.CircularMessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.time.LocalDateTime;

@Service
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
public class TransportService {

    private Logger log = LogManager.getLogger(this.getClass().getSimpleName());

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final CircularMessageRepository circularMessageRepository;

    @Autowired
    public TransportService(KafkaTemplate<String, String> kafkaTemplate,
                            ObjectMapper objectMapper, CircularMessageRepository circularMessageRepository) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
        this.circularMessageRepository = circularMessageRepository;
    }

    public ListenableFuture<SendResult<String, String>> send(String message) {
        log.info("<= sending {}", message);
        return this.kafkaTemplate.send("INPUT_DATA", "IN_KEY", message);
    }



   /* public void send(CircularMessage circularMessage) {
        circularMessage.setMc2Timestamp(LocalDateTime.now());
        circularMessage = circularMessageRepository.save(circularMessage);
        log.info("<= sending {}", circularMessage.toString());
        kafkaCircularMessageTemplate.send("server.circularmessage", circularMessage);
    } */

}

