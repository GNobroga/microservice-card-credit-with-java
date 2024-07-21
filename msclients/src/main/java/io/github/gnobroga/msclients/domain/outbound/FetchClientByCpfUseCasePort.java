package io.github.gnobroga.msclients.domain.outbound;

import java.util.Optional;

import io.github.gnobroga.msclients.domain.entities.Client;

public interface FetchClientByCpfUseCasePort {
    Optional<Client> execute(String value);
}
