package com.example.mc1.service;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.repository.CircularMessageRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.data.util.Pair;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.persistence.Tuple;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Logger;

@Component
public class ScheduledJob {

    private final AtomicReference<Pair<Long, Boolean>> enabled = new AtomicReference<Pair<Long, Boolean>>(Pair.of(0L, false));
    private static final String URI = "ws://mc2:8083/socketHandler";
    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final CircularMessageRepository circularMessageRepository;

    public ScheduledJob(CircularMessageRepository circularMessageRepository) {
        this.circularMessageRepository = circularMessageRepository;
    }

    /**
     * Шедуллер для запуска межсервисного взаимодействия
     */
    @Scheduled(fixedDelayString = "${interaction.interval-ms}")
    public void startInteraction() {
        if (enabled.get().getSecond()) {
            Long currentSession = enabled.get().getFirst();
            CircularMessage circularMessage = new CircularMessage();
            circularMessage.setSessionId(currentSession);
            circularMessage.setMc1Timestamp(LocalDateTime.now());
            circularMessage = circularMessageRepository.save(circularMessage);
            try {
                final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI(URI));
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

    /**
     * Переключить шедуллер на указанное сосотояние
     *
     * @param state состояние, на которое необходимо переключить
     */
    public void toggle(Boolean state, Long sessionId) {
        enabled.set(Pair.of(sessionId, state));
    }

}