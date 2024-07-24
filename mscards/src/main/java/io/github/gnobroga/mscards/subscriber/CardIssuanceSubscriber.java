package io.github.gnobroga.mscards.subscriber;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardIssuanceSubscriber {

    private final ObjectMapper objectMapper;
    
    @RabbitListener(queues = { "${amqp.rabbit.queues.card-issuance}"})
    private void receiveRequestIssuance(String payload) {
        System.out.println(payload);
    }
}
