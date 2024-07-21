package io.github.gnobroga.msclients.adapter.outbound;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gnobroga.msclients.adapter.inbound.entities.ClientEntity;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {
    
}
