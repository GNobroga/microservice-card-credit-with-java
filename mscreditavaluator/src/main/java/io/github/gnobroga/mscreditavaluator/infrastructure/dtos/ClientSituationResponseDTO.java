package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import java.util.List;

import io.github.gnobroga.mscreditavaluator.model.Client;
import io.github.gnobroga.mscreditavaluator.model.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClientSituationResponseDTO {

    
    private Client client;

    private List<ClientCard> cards;
}
