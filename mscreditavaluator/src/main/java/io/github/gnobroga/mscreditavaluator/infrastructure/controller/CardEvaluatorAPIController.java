package io.github.gnobroga.mscreditavaluator.infrastructure.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.gnobroga.mscreditavaluator.domain.exceptions.ClientNotFoundException;
import io.github.gnobroga.mscreditavaluator.domain.exceptions.NoComunicationMicroserviceException;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardEvaluatorRequestDTO;
import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.CardEvaluatorResponseDTO;
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

    @PostMapping
    public ResponseEntity<List<CardEvaluatorResponseDTO>> post(@RequestBody final CardEvaluatorRequestDTO request) {
        try {
            return ResponseEntity.ok(service.processCardEvaluation(request));
        } catch (NoComunicationMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).build();
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/situation/{document}")
    public ResponseEntity<ClientSituationResponseDTO> consultSituation(@PathVariable String document) {
        try {
            return ResponseEntity.ok(service.getClientSituation(document));
        } catch (NoComunicationMicroserviceException e) {
            return ResponseEntity.status(HttpStatus.valueOf(e.getStatus())).build();
        } catch (ClientNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
