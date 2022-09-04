package com.example.mc3.service;

import com.example.mc3.model.CircularMessage;
import com.example.mc3.repository.CircularMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class TransportService {

    private Logger logger = LogManager.getLogger(this.getClass().getSimpleName());
    private final CircularMessageRepository circularMessageRepository;
    private final WebClient webClient;

    public TransportService(CircularMessageRepository circularMessageRepository, WebClient webClient) {
        this.circularMessageRepository = circularMessageRepository;
        this.webClient = webClient;
    }


    @KafkaListener(topics = {"INPUT_DATA"})
    public void consume(final @Payload String message,
                        final @Header(KafkaHeaders.OFFSET) Integer offset,
                        final @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
                        final @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                        final @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                        final @Header(KafkaHeaders.RECEIVED_TIMESTAMP) long ts,
                        final Acknowledgment acknowledgment
    ) throws JsonProcessingException {
        logger.info(String.format("#### -> Consumed message -> TIMESTAMP: %d\n%s\noffset: %d\nkey: %s\npartition: %d\ntopic: %s", ts, message, offset, key, partition, topic));
        ObjectMapper mapper = JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .build();
        CircularMessage circularMessage = mapper.readValue(message, CircularMessage.class);
        circularMessage.setMc3Timestamp(LocalDateTime.now());
        circularMessage = circularMessageRepository.save(circularMessage);
        acknowledgment.acknowledge();
        webClient.post().uri("/apply")
                .body(Mono.just(circularMessage), CircularMessage.class)
                .accept(MediaType.APPLICATION_JSON).retrieve()
                .bodyToMono(CircularMessage.class)
                .timeout(Duration.ofMillis(5000));
    }

}

