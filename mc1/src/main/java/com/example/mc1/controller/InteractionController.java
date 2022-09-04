package com.example.mc1.controller;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.model.InteractionResult;
import com.example.mc1.service.Ms1InteractionService;
import com.example.mc1.service.ScheduledJob;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class InteractionController {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final Ms1InteractionService ms1InteractionService;
    private final ScheduledJob scheduledJob;

    public InteractionController(Ms1InteractionService ms1InteractionService, ScheduledJob scheduledJob) {
        this.ms1InteractionService = ms1InteractionService;
        this.scheduledJob = scheduledJob;
    }

    /**
     * Начать взаимодействие
     *
     */
    @GetMapping("/start")
    public void startInteraction() {
        scheduledJob.toggle(Boolean.TRUE, ms1InteractionService.getNewSessionId());
    }

    /**
     * Остановить взаимодействие и вывести в лог результат
     */
    @GetMapping("/stop")
    public void stopInteraction() {
        Long currentSessionId = ms1InteractionService.getCurrentSessionId();
        scheduledJob.toggle(Boolean.FALSE, currentSessionId);
        InteractionResult interactionResult = ms1InteractionService.getInteractionResult(currentSessionId);
        log.info("INTERACTION TIME IN MS:" + interactionResult.getInteractionTimeInMs() +
                "\nINTERACTION COUNT:" + interactionResult.getInteractionsCount()
        );
    }

    /**
     * Получить сущность из МС3
     *
     * @param requestEntity сущность из МС3
     * @return сущность из МС3
     */
    @PostMapping(value = "/apply",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CircularMessage> applyMessageFromMs3(@RequestBody CircularMessage requestEntity) {
        return ResponseEntity.ok(ms1InteractionService.endInteraction(requestEntity));
    }

}
