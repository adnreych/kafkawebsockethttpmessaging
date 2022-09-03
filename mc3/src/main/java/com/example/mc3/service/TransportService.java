package com.example.mc3.service;

import com.example.mc3.model.CircularMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TransportService {

    private Logger log = LogManager.getLogger(this.getClass().getSimpleName());


    @KafkaListener(id = "CircularMessage", topics = {"server.circularmessage"}, containerFactory = "singleFactory")
    public void consume(CircularMessage circularMessage) {
        log.info("=> consumed {}", circularMessage);
    }

}

