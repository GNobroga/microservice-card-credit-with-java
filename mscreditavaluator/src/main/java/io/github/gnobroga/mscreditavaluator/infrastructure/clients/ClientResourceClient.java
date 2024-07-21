package io.github.gnobroga.mscreditavaluator.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import io.github.gnobroga.mscreditavaluator.infrastructure.dtos.Client;

@FeignClient(name = "client-resource",url = "${client.url}")
public interface ClientResourceClient {
    
    @GetMapping(params = "cpf")
    ResponseEntity<Client> findByDocument(@RequestParam("cpf") String document);
}
