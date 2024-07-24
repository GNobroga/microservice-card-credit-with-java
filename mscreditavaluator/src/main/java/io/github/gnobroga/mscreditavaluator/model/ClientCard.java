package io.github.gnobroga.mscreditavaluator.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientCard {
    
    private String name;

    private String flagCard;

    @JsonProperty("limit")
    private BigDecimal availableLimit;
}
