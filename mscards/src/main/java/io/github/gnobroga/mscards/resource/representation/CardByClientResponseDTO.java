package io.github.gnobroga.mscards.resource.representation;

import java.math.BigDecimal;

import io.github.gnobroga.mscards.entity.ClientCard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CardByClientResponseDTO {
    
    private String name;

    private String flagCard;

    private BigDecimal limit;

    public static CardByClientResponseDTO from(ClientCard source) {
        return CardByClientResponseDTO.builder()
            .flagCard(source.getCard().getFlagCard().name())
            .name(source.getCard().toString())
            .limit(source.getLimit())
            .build();
    }
}
