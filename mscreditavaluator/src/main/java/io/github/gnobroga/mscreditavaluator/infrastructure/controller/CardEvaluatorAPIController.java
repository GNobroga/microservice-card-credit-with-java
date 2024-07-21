package io.github.gnobroga.mscreditavaluator.infrastructure.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.ClientSituationResponseDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.service.CardEvaluatorService;
import lombok.RequiredArgsConstructor;

@Consumes(MediaType.APPLICATION_JSON_VALUE)
@Produces(MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequestMapping("/card-evaluator")
@RequiredArgsConstructor
public class CardEvaluatorAPIController {
    
    private final CardEvaluatorService service;

    @GetMapping("/situation/{document}")
    public ResponseEntity<ClientSituationResponseDTO> consultSituation(@PathVariable String document) {
        return ResponseEntity.ok(service.getClientSituation(document));
    }
}
