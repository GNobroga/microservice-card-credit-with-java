package io.github.gnobroga.msclients.domain.outbound;

import io.github.gnobroga.msclients.domain.entities.Client;

public interface CreateClientUseCasePort {
    Client execute(Client client);
}
