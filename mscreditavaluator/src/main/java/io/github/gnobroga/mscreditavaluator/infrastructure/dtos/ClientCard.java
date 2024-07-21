package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientCard {
    
    private String name;

    private String flagCard;

    private BigDecimal availableLimit;
}
