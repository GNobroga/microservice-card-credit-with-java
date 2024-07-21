package io.github.gnobroga.mscards.repository;

import java.util.UUID;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Repository;

import io.github.gnobroga.mscards.entities.Card;
import lombok.Getter;

@Repository
@Getter
public class CardRepository extends SimpleJpaRepository<Card, UUID> {
    
    private final EntityManager entityManager;

    public CardRepository(EntityManager em) {
        super(Card.class, em);
        this.entityManager = em;
    }
}
