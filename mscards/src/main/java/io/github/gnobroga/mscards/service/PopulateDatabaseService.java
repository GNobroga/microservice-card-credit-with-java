package io.github.gnobroga.mscards.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import io.github.gnobroga.mscards.entity.Card;
import io.github.gnobroga.mscards.entity.Card.FlagCard;
import io.github.gnobroga.mscards.repository.CardRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PopulateDatabaseService {

    private final CardRepository cardRepository;
    
    @Transactional
    public void initialize() {
        final var cardNames = new String[] {
            "Visa Platinum",
            "MasterCard Gold",
            "American Express Blue Cash",
            "Discover It Cash Back",
            "Nomadê",
            "Nubank",
            "Itaú",
            "Bradesco"
        };

        final var cards = new ArrayList<Card>();

        for (var cardName: cardNames) {
            final var card = Card.builder()
                .name(cardName)
                .flagCard(randomFlagCard())
                .basicLimit(randomBigDecimal())
                .income(randomBigDecimal())
                .build();
            cards.add(card);
        }

        cardRepository.saveAll(cards);
    }

    private FlagCard randomFlagCard() {
        final var flagCards = FlagCard.values();
        final var sortedNumber = new Random().nextInt(flagCards.length);
        return flagCards[sortedNumber];
    }

    private BigDecimal randomBigDecimal() {
        final var sortedNumber = 1 + new Random().nextLong(10000L);
        return new BigDecimal(sortedNumber);
    }

}
