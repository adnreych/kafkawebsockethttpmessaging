package com.example.mc1.service;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.repository.CircularMessageRepository;
import org.springframework.stereotype.Service;

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
