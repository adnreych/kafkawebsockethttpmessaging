package com.example.mc1.service;

import com.example.common.model.CircularMessage;
import com.example.common.model.ServiceName;
import com.example.common.service.InteractionService;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Logger;

@Service
public class Ms1InteractionService {

    private final Logger log = Logger.getLogger(this.getClass().getSimpleName());

    private final InteractionService interactionService;


    public Ms1InteractionService(InteractionService interactionService) {
        this.interactionService = interactionService;
    }

    public void startInteraction() {
        log.info("STAAARTED");
        CircularMessage circularMessage = new CircularMessage();
        interactionService.provideInteraction(ServiceName.MS1, circularMessage);
        try {
            final WebsocketClientEndpoint clientEndPoint = new WebsocketClientEndpoint(new URI("ws://localhost:8083/socketHandler"));

            clientEndPoint.addMessageHandler(new WebsocketClientEndpoint.MessageHandler() {
                public void handleMessage(String message) {
                    System.out.println(message);
                }
            });
            clientEndPoint.sendMessage("MMMEEESSAGE}");
            Thread.sleep(5000);

        } catch (InterruptedException ex) {
            System.err.println("InterruptedException exception: " + ex.getMessage());
        } catch (URISyntaxException ex) {
            System.err.println("URISyntaxException exception: " + ex.getMessage());
        }

    }
}
