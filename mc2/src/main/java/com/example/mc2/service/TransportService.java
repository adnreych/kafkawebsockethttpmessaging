package com.example.mc2.service;

import com.example.mc2.model.CircularMessage;
import com.example.mc2.repository.CircularMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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

    public ListenableFuture<SendResult<String, String>> send(CircularMessage circularMessage) throws JsonProcessingException {
        circularMessage.setMc2Timestamp(LocalDateTime.now());
        circularMessage = circularMessageRepository.save(circularMessage);
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        String json = mapper.writeValueAsString(circularMessage);
        log.info("<= sending {}", json);
        return this.kafkaTemplate.send("INPUT_DATA", "IN_KEY", json);
    }



}

