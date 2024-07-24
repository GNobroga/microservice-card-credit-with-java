package io.github.gnobroga.mscards.entity;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "card")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Card {

    public enum FlagCard {
        MASTERCARD, VISA;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @Enumerated(EnumType.STRING)
    private FlagCard flagCard;

    private BigDecimal income;
    
    private BigDecimal basicLimit;
    
    @JsonIgnore
    public boolean isNew() {
        return Objects.isNull(id);
    }
}
