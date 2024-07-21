package io.github.gnobroga.mscards.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.github.gnobroga.mscards.entities.Card;
import io.github.gnobroga.mscards.resource.representation.CardByClientResponseDTO;
import io.github.gnobroga.mscards.resource.representation.CreateCardRequestDTO;
import io.github.gnobroga.mscards.service.CardService;
import io.github.gnobroga.mscards.service.ClientCardService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardResource {

    private final CardService service;

    private final ClientCardService clientCardService;

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody final CreateCardRequestDTO request) {
        return ResponseEntity.status(HttpStatus.OK.value()).body(service.save(request.toCard()));
    }
    
    @GetMapping(params = "income")
    public ResponseEntity<Iterable<Card>> getByIncomeLessThanOrEqual(@RequestParam("income") final Long value) {
        return ResponseEntity.ok(service.findCardsByLessThanEqualIncome(value));
    }
    
    @GetMapping("/{document}")
    public ResponseEntity<List<CardByClientResponseDTO>> listByClientDocument(@PathVariable String document) {
        return ResponseEntity.ok(clientCardService.listByDocument(document)
            .stream()
            .map(CardByClientResponseDTO::from)
            .collect(Collectors.toList())
        );
    }
}
