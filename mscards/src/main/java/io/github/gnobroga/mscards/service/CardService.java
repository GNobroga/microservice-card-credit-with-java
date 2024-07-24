package io.github.gnobroga.mscards.service;

import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.gnobroga.mscards.entity.Card;
import io.github.gnobroga.mscards.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Transactional
@Service
@RequiredArgsConstructor
public class CardService {
    
    private final CardRepository repository;


    public Card save(final Card record) {
        checkEntity(record);
        return repository.save(record);
    }

    public Iterable<Card> findCardsByLessThanEqualIncome(Long income) {
        final var cb = repository.getEntityManager().getCriteriaBuilder();
        final var query = cb.createQuery(Card.class);
        final var root = query.from(Card.class);
        query.where(cb.lessThanOrEqualTo(root.get("income"), BigDecimal.valueOf(income)));
        return repository.getEntityManager().createQuery(query).getResultList();
    }

    private void checkEntity(final Card entity) {
  
        if (Objects.isNull(entity.getName()) || entity.getName().length() < 3) {
            throw new IllegalArgumentException("The name must be greater than 3 caracteres and not be empty.");
        }

        if (Objects.isNull(entity.getFlagCard())) {
            throw new IllegalArgumentException("The flag card is required.");
        }

        if (Objects.isNull(entity.getIncome()) || entity.getIncome().compareTo(BigDecimal.ZERO) == -1) {
            throw new IllegalArgumentException("The income needs be grater than zero and not null.");
        } 
    }


}
