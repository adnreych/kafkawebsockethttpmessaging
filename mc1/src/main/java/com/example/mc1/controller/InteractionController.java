package com.example.mc1.controller;

import com.example.mc1.service.Ms1InteractionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class InteractionController {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final Ms1InteractionService ms1InteractionService;

    public InteractionController(Ms1InteractionService ms1InteractionService) {
        this.ms1InteractionService = ms1InteractionService;
    }

    @GetMapping("/start")
    public ResponseEntity<Integer> startInteraction() {
        ms1InteractionService.startInteraction();
        return ResponseEntity.ok(Integer.MAX_VALUE);
    }

    @GetMapping("/stop")
    public void stopInteraction() {

    }

    @PostMapping("/apply")
    public void applyMessageFromMs3() {

    }

}
