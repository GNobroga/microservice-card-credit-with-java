package io.github.gnobroga.msclients.adapter.outbound;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.gnobroga.msclients.adapter.inbound.entities.ClientEntity;
import io.github.gnobroga.msclients.domain.entities.Client;
import io.github.gnobroga.msclients.domain.outbound.CreateClientUseCasePort;
import lombok.RequiredArgsConstructor;

@Transactional
@RequiredArgsConstructor
@Service
public class CreateClientUseCaseAdapter implements CreateClientUseCasePort {

    private final ClientRepository repository;

    @Override
    public Client execute(final Client client) {
       final var entity = ClientEntity.from(client);
       repository.save(entity);
       return entity.toClient();
    }
    
}
