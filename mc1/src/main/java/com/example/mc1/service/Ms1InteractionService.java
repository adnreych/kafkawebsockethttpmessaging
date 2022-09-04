package com.example.mc1.service;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.model.InteractionResult;
import com.example.mc1.repository.CircularMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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
        return circularMessageRepository.save(circularMessage);
    }

    public Long getNewSessionId() {
        return circularMessageRepository.findAll()
                .stream()
                .mapToLong(CircularMessage::getSessionId)
                .max()
                .orElse(0L) + 1L;
    }

    public Long getCurrentSessionId() {
        return circularMessageRepository.findAll()
                .stream()
                .mapToLong(CircularMessage::getSessionId)
                .max()
                .orElse(0L) + 1L;
    }

    public InteractionResult getInteractionResult(Long sessionId) {
        List<CircularMessage> allBySessionId = circularMessageRepository.findAllBySessionId(sessionId);
        Integer interactionsCount = allBySessionId.size();
        CircularMessage min = allBySessionId.stream()
                .min(Comparator.nullsLast(
                        (e1, e2) -> e2.getMc1Timestamp().compareTo(e1.getMc1Timestamp()))).get();
        CircularMessage max = allBySessionId.stream()
                .max(Comparator.nullsLast(
                        (e1, e2) -> e2.getEndTimestamp().compareTo(e1.getEndTimestamp()))).get();
        long between = ChronoUnit.MILLIS.between(min.getMc1Timestamp(), max.getEndTimestamp());
        InteractionResult interactionResult = new InteractionResult();
        interactionResult.setInteractionsCount(interactionsCount);
        interactionResult.setInteractionTimeInMs(between);
        return interactionResult;
    }
}
