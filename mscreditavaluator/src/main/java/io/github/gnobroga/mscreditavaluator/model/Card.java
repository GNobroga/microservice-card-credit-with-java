package io.github.gnobroga.mscreditavaluator.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Card {
    
    private String id;
    
    private String name;

    private String flagCard;

    private BigDecimal income;

    private BigDecimal basicLimit;
}
