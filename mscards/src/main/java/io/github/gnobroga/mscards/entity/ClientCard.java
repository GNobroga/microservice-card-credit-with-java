package io.github.gnobroga.mscards.entities;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "client_card")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientCard {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String document;

    @ManyToOne
    private Card card;

    @Column(name = "card_limit")
    private BigDecimal limit;
}
