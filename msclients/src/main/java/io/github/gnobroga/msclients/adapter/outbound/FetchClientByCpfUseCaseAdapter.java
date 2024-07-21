package io.github.gnobroga.msclients.adapter.outbound;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import io.github.gnobroga.msclients.adapter.inbound.entities.ClientEntity;
import io.github.gnobroga.msclients.domain.entities.Client;
import io.github.gnobroga.msclients.domain.outbound.FetchClientByCpfUseCasePort;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FetchClientByCpfUseCaseAdapter implements FetchClientByCpfUseCasePort {

    private final ClientRepository repository;

    @Override
    public Optional<Client> execute(final String value) {
        final var matcher = ExampleMatcher.matching()
            .withIgnoreCase()
            .withIgnoreNullValues();
       final var example = Example.of(ClientEntity.builder().cpf(value).build(), matcher);
        return repository.findBy(example, query -> query.first())
            .map(ClientEntity::toClient);
    }
    
}
