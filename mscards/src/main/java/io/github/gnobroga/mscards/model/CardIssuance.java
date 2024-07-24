package io.github.gnobroga.mscards.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CardIssuance {
    
    private String cardId;

    private String document;

    private String address;

    private BigDecimal approvedLimit;
}
