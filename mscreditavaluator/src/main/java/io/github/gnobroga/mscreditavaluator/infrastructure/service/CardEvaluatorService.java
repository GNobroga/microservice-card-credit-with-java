package io.github.gnobroga.mscreditavaluator.infrastructure.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import feign.FeignException.FeignClientException;
import io.github.gnobroga.mscreditavaluator.domain.exceptions.CardIssuanceErrorException;
import io.github.gnobroga.mscreditavaluator.domain.exceptions.ClientNotFoundException;
import io.github.gnobroga.mscreditavaluator.domain.exceptions.NoComunicationMicroserviceException;
import io.github.gnobroga.mscreditavaluator.infrastructure.clients.CardResourceClient;
import io.github.gnobroga.mscreditavaluator.infrastructure.clients.ClientResourceClient;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardEvaluatorRequestDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardEvaluatorResponseDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardIssuanceProtocolResponseDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardIssuanceRequestDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.ClientSituationResponseDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.publisher.CardIssuancePublisher;
import io.github.gnobroga.mscreditavaluator.model.Client;
import io.github.gnobroga.mscreditavaluator.model.ClientCard;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardEvaluatorService {

    private final ClientResourceClient clientResourceClient;

    private final CardResourceClient cardResourceClient;

    private final CardIssuancePublisher cardIssuancePublisher;

    public CardIssuanceProtocolResponseDTO issuanceRequest(CardIssuanceRequestDTO request) {
        try {
            cardIssuancePublisher.send(request);
            var protocol = UUID.randomUUID().toString();
            return new CardIssuanceProtocolResponseDTO(protocol);
        } catch (Exception error) {
            throw new CardIssuanceErrorException(error.getMessage());
        }
    }

    public List<CardEvaluatorResponseDTO> processCardEvaluation(CardEvaluatorRequestDTO request) {
        if (Objects.isNull(request) || Objects.isNull(request.getDocument()) || Objects.isNull(request.getIncome())) {
            throw new IllegalArgumentException("The document and income are required.");
        }

        try {
            Client client = clientResourceClient.findByDocument(request.getDocument()).getBody();
            return cardResourceClient.getByIncome(request.getIncome())
                .getBody()
                .stream()
                .map(card -> {
                    BigDecimal basicLimit = card.getBasicLimit();
                    BigDecimal age = BigDecimal.valueOf(client.getAge());
                    var factor = age.divide(BigDecimal.valueOf(10));
                    var approvedLimit = factor.multiply(basicLimit);
                    return new CardEvaluatorResponseDTO(card.getId(), card.getName(), card.getFlagCard(), approvedLimit);
                }).toList();
        } catch (FeignClientException e) {
          final var status = e.status();
          if (Objects.equals(HttpStatus.NOT_FOUND.value(), status)) {
                throw new ClientNotFoundException();
            }
            throw new NoComunicationMicroserviceException(e.getMessage(), status);
        } 
    }

    public ClientSituationResponseDTO getClientSituation(final String document) {
       try {
        ResponseEntity<Client> clientResponse = clientResourceClient.findByDocument(document);
        ResponseEntity<List<ClientCard>> clientCardResponse = cardResourceClient.getByDocument(document);
        return ClientSituationResponseDTO.builder()
            .client(clientResponse.getBody())
            .cards(clientCardResponse.getBody())
            .build();
       } catch (FeignClientException error) {
            final var status = error.status();
            if (Objects.equals(HttpStatus.NOT_FOUND.value(), status)) {
                throw new ClientNotFoundException();
            }
            throw new NoComunicationMicroserviceException(error.getMessage(), status);
       } 
    }


}
