package io.github.gnobroga.mscards.subscriber;

import java.util.UUID;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.gnobroga.mscards.entity.Card;
import io.github.gnobroga.mscards.entity.ClientCard;
import io.github.gnobroga.mscards.model.CardIssuance;
import io.github.gnobroga.mscards.repository.CardRepository;
import io.github.gnobroga.mscards.repository.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CardIssuanceSubscriber {

    private final ObjectMapper objectMapper;

    private final CardRepository repository;

    private final ClientCardRepository clientCardRepository;
    
    @RabbitListener(queues = { "${amqp.rabbit.queues.card-issuance}"})
    @Transactional
    private void receiveRequestIssuance(@Payload final String payload) throws JsonMappingException, JsonProcessingException {
       try {
            CardIssuance cardIssuance = objectMapper.readValue(payload, CardIssuance.class);
            Card card = repository.findById(UUID.fromString(cardIssuance.getCardId()))
                .orElseThrow();
            ClientCard clientCard = ClientCard.builder()
                .card(card)
                .document(cardIssuance.getDocument())
                .limit(cardIssuance.getApprovedLimit())
                .build();
            clientCardRepository.save(clientCard);
       } catch (Exception error) {
            log.error("Erro ao receber solicitação de emissão de cartão: {}", error.getMessage());
       }
    }
}
