package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardIssuanceRequestDTO {
    
    private String cardId;

    private String document;

    private String address;

    private BigDecimal approvedLimit;
}
