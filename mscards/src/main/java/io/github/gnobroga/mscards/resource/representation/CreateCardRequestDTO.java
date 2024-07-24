package io.github.gnobroga.mscards.resource.representation;

import java.math.BigDecimal;
import java.util.Objects;

import io.github.gnobroga.mscards.entity.Card;
import io.github.gnobroga.mscards.entity.Card.FlagCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateCardRequestDTO {
    
    private String name;

    private String flagCard;

    private BigDecimal income;

    private BigDecimal basicLimit;

    public Card toCard() {

        final var flagCardEnum = FlagCard.valueOf(flagCard);

        if (Objects.isNull(flagCardEnum)) {
            throw new IllegalArgumentException("The flag card does not exists");
        }   

        return Card.builder()
            .name(name)
            .flagCard(flagCardEnum)
            .income(income)
            .basicLimit(basicLimit)
            .build();
    }

}
