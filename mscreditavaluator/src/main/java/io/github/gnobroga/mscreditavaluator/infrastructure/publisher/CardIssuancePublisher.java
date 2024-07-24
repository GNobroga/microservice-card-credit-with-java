package io.github.gnobroga.mscreditavaluator.infrastructure.publisher;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardIssuanceRequestDTO;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CardIssuancePublisher {
    
    private final ObjectMapper objectMapper;
    private final RabbitTemplate rabbitTemplate;
    private final Binding binding;

    public void send(final CardIssuanceRequestDTO request) throws JsonProcessingException {
        final var json = objectMapper.writeValueAsString(request);
        final var message = new Message(json.getBytes());
        rabbitTemplate.send(binding.getExchange(), binding.getRoutingKey(), message);
    }
}


