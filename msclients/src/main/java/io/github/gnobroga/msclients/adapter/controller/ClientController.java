package io.github.gnobroga.msclients.adapter.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.gnobroga.msclients.adapter.inbound.dtos.ClientResponseDTO;
import io.github.gnobroga.msclients.adapter.inbound.dtos.CreateClientRequestDTO;
import io.github.gnobroga.msclients.adapter.outbound.CreateClientUseCaseAdapter;
import io.github.gnobroga.msclients.adapter.outbound.FetchClientByCpfUseCaseAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;



@RequiredArgsConstructor
@RestController
@RequestMapping("/clients")
@Slf4j
public class ClientController {

    private final CreateClientUseCaseAdapter createClientUseCaseAdapter;

    private final FetchClientByCpfUseCaseAdapter fetchClientByCpfUseCaseAdapter;
    
    @PostMapping
    public ResponseEntity<ClientResponseDTO> create(@RequestBody final CreateClientRequestDTO request) {
        final var uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/cpf={cpf}")
            .buildAndExpand(request.getCpf())
            .toUri();
        final var response = ClientResponseDTO.from(createClientUseCaseAdapter.execute(request.toClient()));
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    public ResponseEntity<ClientResponseDTO> getByCpf(@RequestParam String cpf) {
        var optClient = fetchClientByCpfUseCaseAdapter.execute(cpf);
        if (optClient.isEmpty()) {
            return ResponseEntity.notFound().build();
        } 
        return ResponseEntity.ok(ClientResponseDTO.from(optClient.get()));
    }
    
    
}
