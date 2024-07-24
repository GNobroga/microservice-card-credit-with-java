package io.github.gnobroga.mscreditavaluator.infrastructure.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.Card;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.ClientCard;

@FeignClient(name = "card-resource", url = "${card.url}")
public interface CardResourceClient {
    
    @GetMapping
    ResponseEntity<List<Card>> getByIncome(@RequestParam Long income);

    @GetMapping("/{document}")
    ResponseEntity<List<ClientCard>> getByDocument(@PathVariable String document);
}
