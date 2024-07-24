package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CardEvaluatorResponseDTO {

    private String id;
    
    private String card;

    private String flagCard;

    private BigDecimal approvedLimit;
}
