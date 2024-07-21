package io.github.gnobroga.mscreditavaluator.infrastructure.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import io.github.gnobroga.mscreditavaluator.infrastructure.clients.CardResourceClient;
import io.github.gnobroga.mscreditavaluator.infrastructure.clients.ClientResourceClient;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.Client;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.ClientCard;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.ClientSituationResponseDTO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardEvaluatorService {
    
    // msclinets
    // mscards

    private final ClientResourceClient clientResourceClient;

    private final CardResourceClient cardResourceClient;

    public ClientSituationResponseDTO getClientSituation(final String document) {
        ResponseEntity<Client> clientResponse = clientResourceClient.findByDocument(document);
        ResponseEntity<List<ClientCard>> clientCardResponse = cardResourceClient.getByDocument(document);
        return ClientSituationResponseDTO.builder()
            .client(clientResponse.getBody())
            .cards(clientCardResponse.getBody())
            .build();
    }


}
