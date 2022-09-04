package com.example.mc1.controller;

import com.example.mc1.model.CircularMessage;
import com.example.mc1.service.Ms1InteractionService;
import com.example.mc1.service.ScheduledJob;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;
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

    @GetMapping("/start")
    public ResponseEntity<Integer> startInteraction() {
        scheduledJob.toggle(Boolean.TRUE);
        return ResponseEntity.ok(Integer.MAX_VALUE);
    }

    @GetMapping("/stop")
    public void stopInteraction() {
        scheduledJob.toggle(Boolean.FALSE);
    }

    @PostMapping("/apply")
    public ResponseEntity<CircularMessage> applyMessageFromMs3(@RequestBody RequestEntity<CircularMessage> requestEntity) {
        log.info("BOODY" + requestEntity.getBody().toString());
        return ResponseEntity.ok(ms1InteractionService.endInteraction(Objects.requireNonNull(requestEntity.getBody())));
    }

}
