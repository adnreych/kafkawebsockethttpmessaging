package com.example.common.service;

import com.example.common.model.CircularMessage;
import com.example.common.model.ServiceName;
import com.example.common.repository.CircularMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InteractionService {

    private final CircularMessageRepository circularMessageRepository;

    @Autowired
    public InteractionService(CircularMessageRepository circularMessageRepository) {
        this.circularMessageRepository = circularMessageRepository;
    }


    public CircularMessage provideInteraction(ServiceName name, CircularMessage circularMessage) {
        switch (name) {
            case MS1:
                circularMessage.setMc1Timestamp(LocalDateTime.now());
            case MS2:
                circularMessage.setMc2Timestamp(LocalDateTime.now());
            case MS3:
                circularMessage.setMc3Timestamp(LocalDateTime.now());
            case TERMINATE:
                circularMessage.setEndTimestamp(LocalDateTime.now());
            default:
        }
        return circularMessageRepository.save(circularMessage);
    }
}
