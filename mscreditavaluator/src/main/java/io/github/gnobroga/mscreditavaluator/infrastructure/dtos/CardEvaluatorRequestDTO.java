package io.github.gnobroga.mscreditavaluator.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardEvaluatorRequestDTO {
    
    private String document;

    private Long income;
}
